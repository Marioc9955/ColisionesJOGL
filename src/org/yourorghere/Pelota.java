/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;

/**
 *
 * @author Mario
 */
public class Pelota {
    private Vector3f pos,velo;
    private float r;
    
    private long t,ti,tf;
    private float dt;

    public Pelota(Vector3f posici, float r) {
        this.pos = posici;
        this.velo = new Vector3f(((float)Math.random()*5),1,1);
        this.r = r;
        ti = System.currentTimeMillis();
    }

    public void Dibujar(GL gl,GLUT glut){
        dt = t;
        gl.glPushMatrix();
        gl.glTranslated(pos.getX(), pos.getY(), pos.getZ());
        glut.glutWireSphere(r, 20, 20);
        
        
        gl.glPopMatrix();
        tf = System.currentTimeMillis();
        t = tf - ti;
        dt = t - dt;
        pos = Vector3f.Suma(pos, Vector3f.MultEscl(dt/1000, velo));
    }
    
    
    public Vector3f getPosici() {
        return pos;
    }

    public void setPosici(Vector3f posici) {
        this.pos = posici;
    }

    public Vector3f getVelo() {
        return velo;
    }

    public void setVelo(Vector3f velo) {
        this.velo = velo;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }
    
    
    
}
