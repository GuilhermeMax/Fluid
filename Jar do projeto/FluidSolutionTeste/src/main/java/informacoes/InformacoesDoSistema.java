/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informacoes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessosGroup;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.components.Gpu;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import com.github.britooo.looca.api.group.sistema.Sistema;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author fehig
 */
public class InformacoesDoSistema {

    private Looca looca;
    private Memoria memoriaRam;
    private ProcessosGroup grupoDeProcessos;
    private List<Processo> processos;
    private Processador processador;
    private List<Gpu> gpus;
    private Components components;
    private List<Volume> volumeLista;
    private Volume volume;
    private List<Cpu> cpus;
    private DiscosGroup disco;
    private Sistema sistemas;

    /**
     *
     */
    public InformacoesDoSistema() {
        // Api de captura de dados
        Looca looca = new Looca();
        memoriaRam = looca.getMemoria();
        processador = looca.getProcessador();
        grupoDeProcessos = looca.getGrupoDeProcessos();
        processos = grupoDeProcessos.getProcessos();
        disco = looca.getGrupoDeDiscos();
        volumeLista = disco.getVolumes();
        sistemas = looca.getSistema();
        // sistemas.

        // Api de captura de temperatura
        components = JSensors.get.components();
        gpus = components.gpus;
        cpus = components.cpus;

    }

    public Double getPorcentagemUsoRAM() {
        Double memoriaRamPorcentagem;
        Double emUso;

        emUso = memoriaRam.getEmUso().doubleValue();

        Double total = memoriaRam.getTotal().doubleValue();
        memoriaRamPorcentagem = emUso / total * 100;

        return memoriaRamPorcentagem;
    }

    /*public String getPorcentagemUsoCPU() {
        return String.format(
                "%.0f", processador.getUso()
        );
    }*/
    public Double getPorcentagemUsoCPU() {
        return processador.getUso();
    }

    public Double getTempGpu() {
        Double tempGpu = -1.0;
        if (!(gpus.isEmpty())) {
            List<Temperature> temps = gpus.get(0).sensors.temperatures;
            tempGpu = temps.get(temps.size() - 1).value;

        } else {
            System.out.println("Não foi detectada nenhuma placa de vídeo");
        }

        return tempGpu;
    }

    public Double getTempCpu() {
        Double tempCpu;
        List<Temperature> temps = cpus.get(0).sensors.temperatures;
        tempCpu = temps.get(temps.size() - 1).value;

        return tempCpu;
    }

    // Retorna porcentagem disponivel do disco total
    public Integer getPorcentagemDisponivelDisco(Volume disponivel) {
        Double dado;
        Integer porcentagem;
        dado = (disponivel.getDisponivel().doubleValue() * 100) / disponivel.getTotal().doubleValue();
        porcentagem = Math.round(dado.intValue());
        return porcentagem;
    }

    public List<Volume> getListaVolumes() {
        return volumeLista;
    }

    public Integer getQuantidadeDisco() {
        return this.disco.getQuantidadeDeDiscos();
    }

    // Retorna o sistema operacional 
    public String getSistemaOperacional() {
        return sistemas.getSistemaOperacional().toLowerCase();
    }

    // Retorna processos considerados pesados
    public List<Processo> getProcessosPesados() {
        List<Processo> processosPesados = new LinkedList<>();

        for (Processo processo : processos) {
            if (processo.getMemoriaVirtualUtilizada() > 450000000) {
                processosPesados.add(processo);
            }
        }

        return processosPesados;
    }

}
