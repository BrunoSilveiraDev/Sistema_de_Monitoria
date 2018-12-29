package util;

import javax.swing.JOptionPane;

public class Alert {

    public static int alertaExclusao(String nome) {
        return JOptionPane.showConfirmDialog(null, "Você quer realmente excluir " + nome + "?",
                "EXCLUIR", JOptionPane.YES_NO_OPTION);
    }

    public static void alteradoSucesso(String nome) {
        JOptionPane.showMessageDialog(null, nome + " alterado com sucesso.");
    }

    public static void inserirSucesso(String nome) {
        JOptionPane.showMessageDialog(null, nome + " inserido com sucesso.");
    }

    public static void aviso(String aviso) {
        JOptionPane.showMessageDialog(null, aviso);
    }

    public static void avisoErro(String aviso) {
        JOptionPane.showMessageDialog(null,
                aviso,
                "Erro",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void avisoAtencao(String aviso) {
        JOptionPane.showMessageDialog(null,
                aviso,
                "Atenção",
                JOptionPane.WARNING_MESSAGE);
    }

    public static void falhaComDatas() {
        JOptionPane.showMessageDialog(null,
                "Falha ao inserir datas. Use o formato dd/mm/aaaa.",
                "Erro",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void conflitoHorario(String rsp) {
        JOptionPane.showMessageDialog(null,
                rsp,
                "Erro",
                JOptionPane.ERROR_MESSAGE);
    }
}

//PessoaTableModel(recebe uma lista), varClasse List, extends AbstractTableModel, deixar netbeans inserir campos, copiar getValueAt() e fazer getColumnName() a partir dele.
//PessoaPesquisarTela(), varClasse list e dao, criar metodo carregarTela(), chamar no btPesquisar(cpNome.getText()) e quando a janela ganha foco("").
//PessoaCadastrarTela(), varClasse dao e p, criar o metodo salvar(cria nova pessoa e seta vars com nomes dos campos, finaliza com dao.inserir(p) e this.dispose();
// chama o metodo salvar no btSalvar.
//PessoaVisualizarTela(p), copiar PessoaCadastrarTela e colar PessoaVisualizarTela, substituir todos os campos de texto por labels
//com mesmo nome de var, alterar botoes para Alterar e Excluir, varClasse dao e p, criar carregarDados(pegar dados de p e inserir nos campos),
//chamar carregarDados() no construtor.
//PessoaAlterarTela(p), copiar PessoaCadastrarTela e colar PessoaAlterarTela, varClasse dao e p, copiar PessoaVisualizarTela.carregarDados(), chamar carregarDados() no construtor
//mudar dao.inserir(p) para dao.alterar(p), remover new Pessoa de salvar().
//Para excluir use util.Alert.alertaExclusao(p.getNome()), se o retorno for zero dao.deletar(p).
//if (util.Alert.alertaExclusao(p.getNome()) == 0)
