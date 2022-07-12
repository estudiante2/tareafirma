package com.example.tareafirma.entidades;

public class Signaturess
{
    private int id;
    private byte[] firma;
    String descripcion;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public byte[] getFirma()
    {
        return firma;
    }

    public void setFirma(byte[] firma)
    {
        this.firma = firma;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
}