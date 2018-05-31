package com.example.antonio.tresenraya;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Turno {
    private int ordenComienzo;
    List<Integer> v1 = new ArrayList<Integer>();
    List<Integer> v2 = new ArrayList<Integer>();
    List<Integer> v3 = new ArrayList<Integer>();
    List<Integer> v4 = new ArrayList<Integer>();
    List<Integer> v5 = new ArrayList<Integer>();
    List<Integer> v6 = new ArrayList<Integer>();
    List<Integer> v7 = new ArrayList<Integer>();
    List<Integer> v8 = new ArrayList<Integer>();

    public Turno(){
        inicioRdm();
        v1.add(11); v1.add(12); v1.add(13);
        v2.add(21); v2.add(22); v2.add(23);
        v3.add(31); v3.add(32); v3.add(33);
        v4.add(11); v4.add(21); v4.add(31);
        v5.add(12); v5.add(22); v5.add(32);
        v6.add(13); v6.add(23); v6.add(33);
        v7.add(11); v7.add(22); v7.add(33);
        v8.add(13); v8.add(22); v8.add(31);
    }

    private void inicioRdm(){
        int jugadores = 2;
        this.ordenComienzo = new Random().nextInt(jugadores);
    }

    public int getOrdenComienzo(){
        return this.ordenComienzo;
    }

    public int getPosicionTurnoMaquina(List<Integer> listacasillas){
        int casillas, pos;

        casillas = listacasillas.size();
        pos = new Random().nextInt(casillas);

        return pos;
    }

    public int condicionVictoria(List<Integer> j, List<Integer> m, boolean tableroCompleto){
        int res = 3;
        if (j.size() >= 3) {
            if (j.containsAll(v1) || j.containsAll(v2) || j.containsAll(v3) || j.containsAll(v4)
                    || j.containsAll(v5) || j.containsAll(v6) || j.containsAll(v7) || j.containsAll(v8)){
                res = 0;
                return res;
            }
        }

        if (m.size() >= 3) {
            if (m.containsAll(v1) || m.containsAll(v2) || m.containsAll(v3) || m.containsAll(v4)
                   || m.containsAll(v5) || m.containsAll(v6) || m.containsAll(v7) || m.containsAll(v8)){
                res = 1;
                return res;
            }
        }

        if (tableroCompleto && res==3) {
            res = 2;
            return res;
        }

        return res;
    }

    public int comprobarMovimientoJugador(List<Integer> j){
        int res = 1;

        if (j.containsAll(v1) || j.containsAll(v2) || j.containsAll(v3) || j.containsAll(v4)
                || j.containsAll(v5) || j.containsAll(v6) || j.containsAll(v7) || j.containsAll(v8)){
            res = 0;

        }
        return res;
    }

    public int getPosicionTurnoMaquinav2(List<Integer> nm, List<Integer> jm, List<Integer> mm){
        List<Integer> j = new ArrayList<Integer>();
        j.addAll(jm);
        List<Integer> m = new ArrayList<Integer>();
        m.addAll(mm);
        int res = 0;


        if(m.size()>=2){
            if(!m.contains(11) && nm.contains(11)) {
                m.add(11);
                res = comprobarMovimientoJugador(m);
                if(res == 0) {
                    System.out.println("Proximo movimiento para ganar 11");
                    return nm.indexOf(11);
                }
                else
                    m.remove(m.indexOf(11));
            }
            if(!m.contains(12) && nm.contains(12)) {
                m.add(12);
                res = comprobarMovimientoJugador(m);
                if(res == 0) {
                    System.out.println("Proximo movimiento para ganar 12");
                    return nm.indexOf(12);
                }
                else
                    m.remove(m.indexOf(12));
            }
            if(!m.contains(13) && nm.contains(13)) {
                m.add(13);
                res = comprobarMovimientoJugador(m);
                if(res == 0) {
                    System.out.println("Proximo movimiento para ganar 13");
                    return nm.indexOf(13);
                }
                else
                    m.remove(m.indexOf(13));
            }
            if(!m.contains(21) && nm.contains(21)) {
                m.add(21);
                res = comprobarMovimientoJugador(m);
                if(res == 0) {
                    System.out.println("Proximo movimiento para ganar 21");
                    return nm.indexOf(21);
                }
                else
                    m.remove(m.indexOf(21));
            }
            if(!m.contains(22) && nm.contains(22)) {
                m.add(22);
                res = comprobarMovimientoJugador(m);
                if(res == 0) {
                    System.out.println("Proximo movimiento para ganar 22");
                    return nm.indexOf(22);
                }
                else
                    m.remove(m.indexOf(22));
            }
            if(!m.contains(23) && nm.contains(23)) {
                m.add(23);
                res = comprobarMovimientoJugador(m);
                if(res == 0) {
                    System.out.println("Proximo movimiento para ganar 23");
                    return nm.indexOf(23);
                }
                else
                    m.remove(m.indexOf(23));
            }
            if(!m.contains(31) && nm.contains(31)) {
                m.add(31);
                res = comprobarMovimientoJugador(m);
                if(res == 0) {
                    System.out.println("Proximo movimiento para ganar 31");
                    return nm.indexOf(31);
                }
                else
                    m.remove(m.indexOf(31));
            }
            if(!m.contains(32) && nm.contains(32)) {
                m.add(32);
                res = comprobarMovimientoJugador(m);
                if(res == 0) {
                    System.out.println("Proximo movimiento para ganar 32");
                    return nm.indexOf(32);
                }
                else
                    m.remove(m.indexOf(32));
            }
            if(!m.contains(33) && nm.contains(33)) {
                m.add(33);
                res = comprobarMovimientoJugador(m);
                if(res == 0) {
                    System.out.println("Proximo movimiento para ganar 33");
                    return nm.indexOf(33);
                }
                else
                    m.remove(m.indexOf(33));
            }

        }


        if(j.size()>=2){
            if(!j.contains(11) && nm.contains(11)) {
                j.add(11);
                res = comprobarMovimientoJugador(j);
                if(res == 0) {
                    System.out.println("Proximo movimiento jugador 11");
                    return nm.indexOf(11);
                }
                else
                    j.remove(j.indexOf(11));
            }
            if(!j.contains(12) && nm.contains(12)) {
                j.add(12);
                res = comprobarMovimientoJugador(j);
                if(res == 0) {
                    System.out.println("Proximo movimiento jugador 12");
                    return nm.indexOf(12);
                }
                else
                    j.remove(j.indexOf(12));
            }
            if(!j.contains(13) && nm.contains(13)) {
                j.add(13);
                res = comprobarMovimientoJugador(j);
                if(res == 0) {
                    System.out.println("Proximo movimiento jugador 13");
                    return nm.indexOf(13);
                }
                else
                    j.remove(j.indexOf(13));
            }
            if(!j.contains(21) && nm.contains(21)) {
                j.add(21);
                res = comprobarMovimientoJugador(j);
                if(res == 0) {
                    System.out.println("Proximo movimiento jugador 21");
                    return nm.indexOf(21);
                }
                else
                    j.remove(j.indexOf(21));
            }
            if(!j.contains(22) && nm.contains(22)) {
                j.add(22);
                res = comprobarMovimientoJugador(j);
                if(res == 0) {
                    System.out.println("Proximo movimiento jugador 22");
                    return nm.indexOf(22);
                }
                else
                    j.remove(j.indexOf(22));
            }
            if(!j.contains(23) && nm.contains(23)) {
                j.add(23);
                res = comprobarMovimientoJugador(j);
                if(res == 0) {
                    System.out.println("Proximo movimiento jugador 23");
                    return nm.indexOf(23);
                }
                else
                    j.remove(j.indexOf(23));
            }
            if(!j.contains(31) && nm.contains(31)) {
                j.add(31);
                res = comprobarMovimientoJugador(j);
                if(res == 0) {
                    System.out.println("Proximo movimiento jugador 31");
                    return nm.indexOf(31);
                }
                else
                    j.remove(j.indexOf(31));
            }
            if(!j.contains(32) && nm.contains(32)) {
                j.add(32);
                res = comprobarMovimientoJugador(j);
                if(res == 0) {
                    System.out.println("Proximo movimiento jugador 32");
                    return nm.indexOf(32);
                }
                else
                    j.remove(j.indexOf(32));
            }
            if(!j.contains(33) && nm.contains(33)) {
                j.add(33);
                res = comprobarMovimientoJugador(j);
                if(res == 0) {
                    System.out.println("Proximo movimiento jugador 33");
                    return nm.indexOf(33);
                }
                else
                    j.remove(j.indexOf(33));
            }

        }
        System.out.println("No hay proximo movimiento");
        return getPosicionTurnoMaquina(nm);
    }
}
