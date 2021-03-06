package br.ufrn.ase.domain;

import java.util.Date;

import br.ufrn.ase.util.DateUtil;
import br.ufrn.ase.util.StringUtil;

public class LogOperacao {

	private String action;
	private Date horario;
	private String parametros;
	private int tempo;
	private boolean erro;
	private String trace;
	private int idOperacao;
	private int idAcessoPublico;
	private String mensagens;
	private Sistema sistema;
	private RegistroEntrada registroEntrada;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public String getParametros() {
		return parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public boolean isErro() {
		return erro;
	}

	public void setErro(boolean erro) {
		this.erro = erro;
	}

	public String getTrace() {
		return trace;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}

	public int getIdOperacao() {
		return idOperacao;
	}

	public void setIdOperacao(int idOperacao) {
		this.idOperacao = idOperacao;
	}

	public int getIdAcessoPublico() {
		return idAcessoPublico;
	}

	public void setIdAcessoPublico(int idAcessoPublico) {
		this.idAcessoPublico = idAcessoPublico;
	}

	public String getMensagens() {
		return mensagens;
	}

	public void setMensagens(String mensagens) {
		this.mensagens = mensagens;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
	
	public RegistroEntrada getRegistroEntrada() {
		return registroEntrada;
	}

	public void setRegistroEntrada(RegistroEntrada registroEntrada) {
		this.registroEntrada = registroEntrada;
	}

	@Override
	public String toString() {
		return "LogOperacao [action=" + action + ", horario=" + DateUtil.formatDateWithMs(horario) + ", parametros="
				+ parametros + ", tempo=" + tempo + ", erro=" + erro + ", trace=" + StringUtil.getFirstLine(trace)
				+ ", idOperacao=" + idOperacao + ", idAcessoPublico=" + idAcessoPublico + ", mensagens=" + mensagens
				+ ", sistema=" + sistema + "]";
	}

}
