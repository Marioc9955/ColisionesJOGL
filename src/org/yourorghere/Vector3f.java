/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

/**
 *
 * @author Mario
 */
public class Vector3f {

    private float x, y, z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3f Suma(Vector3f a, Vector3f b) {
        return new Vector3f(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static Vector3f MultEscl(float a, Vector3f b) {
        return new Vector3f(a * b.x, a * b.y, a * b.z);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float get(int i) {
        float c = 0;
        switch (i) {
            case 0:
                c = x;
                break;
            case 1:
                c = y;
                break;
            case 2:
                c = z;
                break;
        }
        return c;
    }

    public void set(int i, float c) {
        switch (i) {
            case 0:
                x = c;
                break;
            case 1:
                y = c;
                break;
            case 2:
                z = c;
                break;
        }
    }

    public float norma() {
        return (float)Math.sqrt(x * x + y * y + z * z);
    }

    public void normalizar() {        
        x = x / norma();
        y = y / norma();
        z = z / norma();
    }
    
    public Vector3f normalizado() {
        float xi,yi,zi;
        xi = this.x / norma();
        yi = this.y / norma();
        zi = this.z / norma();
        return new Vector3f(xi,yi,zi);
    }
    
    public String toString(){
        return "( "+x+", "+y+", "+z+" )";
    }
}
