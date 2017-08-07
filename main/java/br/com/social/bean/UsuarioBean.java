package br.com.social.bean;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.social.dao.UsuarioDao;
import br.com.social.modelo.Usuario;

@Model
public class UsuarioBean {

	private ControladorBean bean = new ControladorBean();
	private Usuario usuario = new Usuario();
	private FacesContext context;
	private String pagina;

	public UsuarioBean() {
		this.context = FacesContext.getCurrentInstance();
		this.pagina = "/usuario/cadastroUsuario.xhtml";
	}

	@Inject
	private UsuarioDao usuarioDao;

	@Transactional
	public void cadastrar() {
		if (null != usuarioDao.consultaUsuario(getUsuario().getEmail())) {
			getContext().addMessage(null, new FacesMessage("O email " + getUsuario().getEmail()
					+ " já está cadastrado, por favor informe um novo!"));
		} else {
			usuarioDao.salvar(getUsuario());
			setUsuario(usuarioDao.consultaUsuario(getUsuario()));
			setPagina("/usuario/usuario.xhtml");
			if (null != getUsuario()) {
				getContext().getExternalContext().getSessionMap().put("usuarioLogado", getUsuario());

			} else {
				getContext().addMessage(null, new FacesMessage("Usuário não encontrado."));
			}
		}
		getContext().getExternalContext().getFlash().setKeepMessages(true);
		bean.navegar(getPagina());
	}

	@Transactional
	public void contatos() {

	}

	@Transactional
	public void conversas() {

	}

	private void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	private void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getPagina() {
		return this.pagina;
	}

	public FacesContext getContext() {
		return context;
	}

}
