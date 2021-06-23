package br.projetoFinal.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Infracao implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String descricao;
	private Date data;
	private String gravidade;
	private float valor;
	private String local;
	private Date data_vencimento;
	private Veiculo veiculo;
	
	public Infracao() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}
	
	public String getDataDMA() {
		return (new SimpleDateFormat("dd/MM/yyyy")).format(this.data);
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getGravidade() {
		return gravidade;
	}

	public void setGravidade(String gravidade) {
		this.gravidade = gravidade;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Date getData_vencimento() {
		return data_vencimento;
	}
	
	public String getData_vencimentoDMA() {
		return (new SimpleDateFormat("dd/MM/yyyy")).format(this.data_vencimento);
	}

	public void setData_vencimento(Date data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

}