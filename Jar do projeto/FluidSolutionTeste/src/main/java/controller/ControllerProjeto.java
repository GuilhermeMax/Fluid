/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Projeto;
import view.TelaProjetos;

/**
 *
 * @author fehig
 */
public class ControllerProjeto {

    private TelaProjetos view;
    private controller.ControllerBancoDeDados banco;
    private DefaultTableModel modelo;
    private List<Projeto> projetos;
    private ControllerSessao sessao;

    public ControllerProjeto(TelaProjetos view) throws IOException {
        this.banco = new ControllerBancoDeDados();
        this.sessao = ControllerSessao.getSessaoAtiva();        
        this.projetos = banco.selectAllFromProjeto();
        this.view = view;
        this.modelo = (DefaultTableModel) view.getTblProjeto().getModel();
        view.getLblUsername().setText(sessao.getUsuarioAtivo().getUsername());
        view.getLblCargo().setText(sessao.getUsuarioAtivo().getTipoUsuario());
    }

    public void criarProjeto() throws IOException {
        String nome = view.getTxtNome().getText();
        String data = view.getTxtData().getText();
        String etapa = view.getTxtEtapa().getText();
        Integer fkEmpresa = sessao.getUsuarioAtivo().getFkEmpresa();
        Projeto projeto = new Projeto(nome, etapa, fkEmpresa, data);
        banco.insertIntoProjeto(nome, etapa, fkEmpresa, data);
        projetos.add(projeto);

        atualizarTabela();
    }

    public void removerProjeto() throws IOException {
        int posicao = view.getTblProjeto().getSelectedRow();
        String nomeDoProjetoDeletado = projetos.get(posicao).getNomeProjeto();
        String etapaDoProjetoDeletado = projetos.get(posicao).getEtapaProjeto();

        if (posicao == -1) {
            System.out.println("Nenhum projeto selecionado");
        } else {
            banco.deleteFromProjeto(nomeDoProjetoDeletado, etapaDoProjetoDeletado);
            projetos.remove(posicao);
            atualizarTabela();
        }

    }

    public void atualizarTabela() {
        modelo.setNumRows(0);

        for (Projeto projeto : projetos) {
            modelo.addRow(new Object[]{
                projeto.getNomeProjeto(),
                projeto.getEtapaProjeto(),
                projeto.getDataString()
            });
        }
        view.setModelo(modelo);
    }

}
