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
	public void cadastrar() {
		if (null == usuarioDao.consultaUsuario(getUsuario().getEmail())) {
			usuarioDao.salvar(getUsuario());
			setUsuario(usuarioDao.consultaUsuario(getUsuario()));
			if (null != getUsuario()) {
				getContext().getExternalContext().getSessionMap().put("usuarioLogado", getUsuario());
				setPagina("/usuario/usuario.xhtml");
			}
		}
		bean.navegar(getPagina());
	}

	@Transactional
	public void adicionarContato() {
		setUsuario((Usuario) getContext().getExternalContext().getSessionMap().get("usuarioLogado"));
		setContato(usuarioDao.consultaUsuario(getContato().getEmail()));
		if (null != getContato()) {
			getUsuario().adicionaContatos(getContato());
			// getContext().getExternalContext().getSessionMap().put("contatos",
			// usuarioDao.buscaContatos(getUsuario()));
			System.out.println(getContext().getExternalContext().getSessionMap().get("contatos"));
			setPagina("/usuario/contatos.xhtml");
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
