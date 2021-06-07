/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.github.britooo.looca.api.group.discos.Volume;
import controller.ControllerBancoDeDados;
import controller.ControllerConnectionAzure;
import controller.ControllerDashboard;
import informacoes.InformacoesDoSistema;
import static java.awt.image.ImageObserver.FRAMEBITS;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import model.Projeto;
import model.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author fehig
 */
public class Teste {

    Volume volume;
    List<Volume> volumeLista;
    
    ControllerDashboard dash;
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
//        InformacoesDoSistema infoSistema = new InformacoesDoSistema();


//        double porcentagemRAM = infoSistema.getPorcentagemUsoRAM();
//        String porcentagemCPU = infoSistema.getPorcentagemUsoCPU();
        
//
//        System.out.printf("Uso de RAM: %.1f%%\n",porcentagemRAM);
//        System.out.println("Uso de CPU: " +porcentagemCPU);
//        System.out.println("Temperatura da CPU: " +infoSistema.getTempCpu() + "ºC");
//        System.out.println("Temperatura da GPU: " + infoSistema.getTempGpu() + "ºC");
//        
//        ControllerConnectionAzure connectionConfig = new ControllerConnectionAzure();
//
//        JdbcTemplate con = new JdbcTemplate(connectionConfig.getDataSouce());
//
//        System.out.println( con.queryForList("Select username from usuario"));
//        
//        System.out.println( con.query("Select username,senha from usuario", new BeanPropertyRowMapper(Usuario.class)));;
//         Select dos campos
//         Usuario usuario= new Usuario();
//         usuario.setUsername("Varchar campo");
//        Double porcentagem;
//        List<Volume> volumes = infoSistema.getListaVolumes();
//        for(Volume v:volumes){
//            porcentagem = infoSistema.getPorcentagemDisponivelDisco(v);
//            System.out.println(v);
//            System.out.printf("Porcentagem: %.2f%% \n", porcentagem);
//        }
        
        //System.out.println(InetAddress.getLocalHost().getHostName());  
        ControllerBancoDeDados banco = new ControllerBancoDeDados();
//        banco.insertIntoProjeto("Procurando Nemo", "Correção de cor", 1, "29/06/2021");
    
        banco.deleteFromProjeto("Procurando Nemo", "Correção de cor");

        List<Projeto> projetos = banco.selectAllFromProjeto();

        for (Projeto p : projetos){
            System.out.println(p);
        }
    }

}
