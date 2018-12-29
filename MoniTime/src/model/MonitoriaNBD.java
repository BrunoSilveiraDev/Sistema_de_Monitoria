/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author snow
 */
public class MonitoriaNBD {
//moaid, moamonusumatricula, moadissigla, moasalid, moasaldiasemana, moasalhoraini, moasalhorafim

    private int id;
    private Monitor monitor;
    private Sala sala;

    public MonitoriaNBD() {
    }

    public MonitoriaNBD(Monitor monitor, Sala sala) {
        this.monitor = monitor;
        this.sala = sala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

}
