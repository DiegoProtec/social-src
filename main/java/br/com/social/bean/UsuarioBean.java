package br.com.social.bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.social.dao.UsuarioDao;
import br.com.social.modelo.Usuario;

@Named
@RequestScoped
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
			this.context.addMessage(null, new FacesMessage(
					"O email " + getUsuario().getEmail() + " já está cadastrado, por favor informe um novo!"));
		} else {
			usuarioDao.salvar(getUsuario());
			setUsuario(usuarioDao.consultaUsuario(getUsuario()));
			setPagina("/usuario/usuario.xhtml");
			if (null != getUsuario()) {
				getContext().getExternalContext().getSessionMap().put("usuarioLogado", getUsuario());

			} else {
				this.context.addMessage(null, new FacesMessage("Usuário não encontrado."));
			}
		}
		getContext().getExternalContext().getFlash().setKeepMessages(true);
		bean.navegar(getPagina());
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
