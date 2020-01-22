package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.GLUT;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 * Colisiones.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel)
 * <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Colisiones implements GLEventListener,KeyListener {

    Pelota p;
    int cam = 1;
    float radio = 2, angulo = 0;

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Colisiones());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
        drawable.addKeyListener(this);
        p = new Pelota(new Vector3f(0, 0, 0), 1);

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!

            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        GLUT glut = new GLUT();
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        // Move the "drawing cursor" around
        gl.glTranslatef(0f, 0.0f, -6.0f);
        //CAMARAS
        if (cam == 1) {
            glu.gluLookAt(0, 0, 5, 0, 0, 0, 0, 1, 0);
        }
        if (cam == 2) {
            glu.gluLookAt((radio * Math.sin(angulo)), 1, (radio * Math.cos(angulo)), 0, 0, 0, 0, 1, 0);
        }
        if (cam == 3) {
            glu.gluLookAt(1, (radio * Math.sin(angulo)), (radio * Math.cos(angulo)), 0, 0, 0, 0, 1, 0);
        }
        //CAMARA CENITAL
        if (cam == 4) {
            glu.gluLookAt(0, 5, 0.1f, 0, 0, 0, 0, 1, 0);
        }

        //FINAL CAMARAS
        p.Dibujar(gl, glut);
        
        gl.glLineWidth(10);
        glut.glutWireCube(5);

        if (p.getPosici().getX() + p.getR() > 5 / 2) {
            p.setVelo(new Vector3f(-p.getVelo().getX(), p.getVelo().getY(), p.getVelo().getZ()));
        } else if (p.getPosici().getX() - p.getR() < -5 / 2) {
            p.setVelo(new Vector3f(-p.getVelo().getX(), p.getVelo().getY(), p.getVelo().getZ()));
        }
        if (p.getPosici().getY() + p.getR() > 5 / 2) {
            p.setVelo(new Vector3f(p.getVelo().getX(), -p.getVelo().getY(), p.getVelo().getZ()));
        } else if (p.getPosici().getY() - p.getR() < -5 / 2) {
            p.setVelo(new Vector3f(p.getVelo().getX(), -p.getVelo().getY(), p.getVelo().getZ()));
        }
        if (p.getPosici().getZ() + p.getR() > 5 / 2) {
            p.setVelo(new Vector3f(p.getVelo().getX(), p.getVelo().getY(), -p.getVelo().getZ()));
        } else if (p.getPosici().getZ() - p.getR() < -5 / 2) {
            p.setVelo(new Vector3f(p.getVelo().getX(), p.getVelo().getY(), -p.getVelo().getZ()));
        }

        // Flush all drawing operations to the graphics card
        gl.glFlush();
        angulo = angulo + 0.01f;
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

        //------------------*************************------------------//
        //EVENTOS DE TECLADO PARA LAS CAMARAS
        if (e.getKeyCode() == KeyEvent.VK_1) {
            cam = 1;

        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            cam = 2;

        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            cam = 3;

        }
        if (e.getKeyCode() == KeyEvent.VK_4) {
            cam = 4;

        }

    }

    public void keyReleased(KeyEvent e) {
    }
}
