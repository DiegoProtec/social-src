package br.com.social.bean;

import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.social.dao.UsuarioDao;
import br.com.social.modelo.Usuario;

@Model
public class ContatoBean {

	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = null;
	private ControladorBean bean = new ControladorBean();
	private FacesContext context;
	private Map<String, String> params;
	private String param;

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

	public void procurar(AjaxBehaviorEvent ev) {
		getUsuario().setNome((getUsuario().getNome().trim()));
		if (!("" == getUsuario().getNome() || null == getUsuario().getNome() || !(getUsuario().getNome().isEmpty()))) {
			setUsuarios(usuarioDao.procura(getUsuario()));
			if (null != getUsuarios()) {
				System.out.println("usuario: " + getUsuarios() + "encontrado");
			} else {
				System.out.println("usuario não encontrado.");
			}
		}
	}

	@Transactional
	public void adicionar() {

	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	private void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Map<String, String> getParams() {
		return this.params;
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
