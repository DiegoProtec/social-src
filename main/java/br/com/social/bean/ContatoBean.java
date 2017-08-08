package br.com.social.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.social.dao.UsuarioDao;
import br.com.social.modelo.Usuario;

@Model
public class ContatoBean {

	private ControladorBean bean = new ControladorBean();
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = null;
	private FacesContext context;
	private Map<String, String> params;
	private String param;
	List<String> campos = new ArrayList<>();

	@Inject
	private UsuarioDao usuarioDao;

	public ContatoBean() {
		this.context = FacesContext.getCurrentInstance();
	}

	@Transactional
	public void listaContatos() {
		setParams(getContext().getExternalContext().getRequestParameterMap());
		setParam(getParams().get("pagina"));
		bean.navegar(getParam());
	}

	public void procurarEmail(AjaxBehaviorEvent ev) {
		getUsuario().setEmail((getUsuario().getEmail().trim()));
		setUsuario(usuarioDao.procuraEmail(getUsuario()));
		if (!(getUsuario().getEmail().equals(
				((Usuario) getContext().getExternalContext().getSessionMap().get("usuarioLogado")).getEmail()))) {
			if (null != getUsuario()) {
				getContext().getExternalContext().getSessionMap().put("contato", getUsuario());
				getCampos().add("contatosForm:email");
				limpaDados();
			} else {
				System.out.println("usuario não encontrado.");
			}
		} else {
			setUsuario(null);
		}
	}

	private void limpaDados() {
		UIViewRoot view = getContext().getViewRoot();
		view.resetValues(getContext(), getCampos());
		this.setUsuario(null);
	}

	@Transactional
	public void adicionar() {

	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	/*
	 * private void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios;
	 * }
	 */

	public Map<String, String> getParams() {
		return this.params;
	}

	public List<String> getCampos() {
		return campos;
	}

	public void setCampos(List<String> campos) {
		this.campos = campos;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getParam() {
		return this.param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public FacesContext getContext() {
		return this.context;
	}

}
