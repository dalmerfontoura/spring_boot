package pro.fontoura.cursomc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import pro.fontoura.cursomc.domain.Cidade;
import pro.fontoura.cursomc.domain.Cliente;
import pro.fontoura.cursomc.domain.Endereco;
import pro.fontoura.cursomc.domain.enuns.Perfil;
import pro.fontoura.cursomc.domain.enuns.TipoPessoa;
import pro.fontoura.cursomc.dto.ClienteDTO;
import pro.fontoura.cursomc.dto.ClienteNewDTO;
import pro.fontoura.cursomc.repositories.ClienteRepository;
import pro.fontoura.cursomc.repositories.EnderecoRepository;
import pro.fontoura.cursomc.security.UserSpringSecurity;
import pro.fontoura.cursomc.services.exceptions.AuthorizationException;
import pro.fontoura.cursomc.services.exceptions.DataIntegrityException;
import pro.fontoura.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService implements ServiceInterface<Cliente> {

	@Value("${img.prefix.client.profile}")
	private String prefix;

	@Value("${img.profile.size}")
	private int size;

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private BCryptPasswordEncoder bCrypt;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	public Cliente find(Integer id) {

		UserSpringSecurity uss = UserService.authenticated();
		if (uss == null || !uss.hasRole(Perfil.ADMIN) && !id.equals(uss.getId())) {
			throw new AuthorizationException("Acesso Negado!");
		}

		Optional<Cliente> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(id, Cliente.class.getName()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pro.fontoura.cursomc.services.ServiceInterface#findAll()
	 */
	@Override
	public List<Cliente> findAll() {
		return repository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pro.fontoura.cursomc.services.ServiceInterface#findToPages(int, int,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Page<Cliente> findToPages(int page, int linesPerPages, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);

		return repository.findAll(pageRequest);
	}

	public Cliente findByEmail(String email) {
		UserSpringSecurity uss = UserService.authenticated();
		if (uss == null || !uss.hasRole(Perfil.ADMIN) && !email.equals(uss.getUsername())) {
			throw new AuthorizationException("Acesso Negado!");
		}
		Cliente cli = repository.findByEmail(email);
		if (cli == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + uss.getId() + ", Tipo " + Cliente.class.getName());
		}
		return cli;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pro.fontoura.cursomc.services.ServiceInterface#insert(pro.fontoura.cursomc.
	 * domain.Cliente)
	 */
	@Override
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());

		return obj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pro.fontoura.cursomc.services.ServiceInterface#update(pro.fontoura.cursomc.
	 * domain.Cliente)
	 */
	@Override
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pro.fontoura.cursomc.services.ServiceInterface#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Cliente que possui pedidos relacionados.");
		}
	}

	/**
	 * @param objDto
	 * @return
	 */
	public Cliente fromDTO(ClienteDTO objDto) {
		Cliente obj = new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
		return obj;
	}

	/**
	 * @param objDto
	 * @return
	 */
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), bCrypt.encode(objDto.getSenha()),
				objDto.getCpfOuCnpj(), TipoPessoa.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefone().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null)
			cli.getTelefone().add(objDto.getTelefone2());
		if (objDto.getTelefone3() != null)
			cli.getTelefone().add(objDto.getTelefone3());

		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSpringSecurity UserSecurity = UserService.authenticated();
		if (UserSecurity == null) {
			throw new AuthorizationException("Acesso Negado!");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);

		String fileName = prefix + UserSecurity.getId() + ".jpg";
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
}
