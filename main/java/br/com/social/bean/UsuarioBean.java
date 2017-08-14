package br.com.social.bean;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.social.dao.UsuarioDao;
import br.com.social.modelo.Usuario;

@Model
public class UsuarioBean {

	private ControladorBean bean = new ControladorBean();
	private Usuario usuario = new Usuario();
	private Usuario contato = new Usuario();
	private FacesContext context;
	private String pagina;

	@Inject
	private UsuarioDao usuarioDao;

	public UsuarioBean() {
		setContext(FacesContext.getCurrentInstance());
		setPagina("/cadastro.xhtml");
	}

	@Transactional
	public void cadastrarUsuario() {
		if (null == usuarioDao.consultarUsuario(getUsuario().getEmail())) {
			usuarioDao.salvar(getUsuario());
			setUsuario(usuarioDao.consultarUsuario(getUsuario().getId()));
			if (null != getUsuario()) {
				getContext().getExternalContext().getSessionMap().put("usuarioLogado", getUsuario());
				setPagina("/usuario/usuario.xhtml");
			}
		}
		bean.navegar(getPagina());
	}

	@Transactional
	public void adicionarContato() {
		setPagina("/usuario/contato/cadastroContato.xhtml");
		setUsuario((Usuario) getContext().getExternalContext().getSessionMap().get("usuarioLogado"));
		for (Usuario contato : getUsuario().getContatos()) {
			System.out.println(contato.getEmail());
		}
		System.out.println("antes de chamar consultarUsuario por email");
		setContato(usuarioDao.consultarUsuario(getContato().getEmail()));
		System.out.println(getContato().getEmail());
		if (null != getContato()) {
			getUsuario().setContatos(getContato());
			System.out.println("antes de chamar alterarLista");
			setUsuario(usuarioDao.alterarLista(getUsuario()));
			setPagina("/usuario/contatos.xhtml");
			for (Usuario contato : getUsuario().getContatos()) {
				System.out.println(contato.getEmail());
			}
		} else {
			bean.navegar(getPagina());
		}
	}

	private void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public Usuario getContato() {
		return contato;
	}

	public void setContato(Usuario contato) {
		this.contato = contato;
	}

	private void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getPagina() {
		return this.pagina;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}

	public FacesContext getContext() {
		return context;
	}

}
