package LabWork.CommonResources;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Александр Сергеевич on 14.04.2016.
 */

public class CommonResources2
{
    private boolean BooleanVar;
    private byte    ByteVar;
    private char 	CharacterVar;
    private float 	FloatVar;
    private int 	IntegerVar;
    private long 	LongVar;
    private short 	ShortVar;
    private double 	DoubleVar;


    public void put(boolean _BooleanVar)
    {
        BooleanVar =_BooleanVar;
    }
    public void put(byte _ByteVar)
    {
        ByteVar = _ByteVar;
    }
    public void put(char _CharacterVar)
    {
        CharacterVar = _CharacterVar;
    }
    public void put(float _FloatVar)
    {
        FloatVar = _FloatVar;
    }
    public void put(int _IntegerVar)
    {
        IntegerVar = _IntegerVar;
    }
    public void put(long _LongVar)
    {
        LongVar = _LongVar;
    }
    public void put(short _ShortVar)
    {
        ShortVar = _ShortVar;
    }
    public void put(double _DoubleVar)
    {
        DoubleVar = _DoubleVar;
    }

    public boolean getBooleanVar()
    {
        return BooleanVar;
    }
    public byte getByteVar()
    {
        return ByteVar;
    }
    public char getCharacterVar()
    {
        return CharacterVar;
    }
    public float getFloatVar()
    {
        return FloatVar;
    }
    public int getIntegerVar()
    {
        return IntegerVar;
    }
    public long getLongVar()
    {
        return LongVar;
    }
    public short getShortVar()
    {
        return ShortVar;
    }
    public double getDoubleVar()
    {
        return DoubleVar;
    }

    @Override
    public String toString() {
        return "BooleanVar: " + BooleanVar + ";\n" +
               "ByteVar: " + ByteVar + ";\n" +
               "CharacterVar: " + CharacterVar + ";\n" +
               "FloatVar: " + FloatVar + ";\n" +
               "IntegerVar: " + IntegerVar + ";\n" +
               "LongVar: " + LongVar + ";\n" +
               "ShortVar: " + ShortVar + ";\n" +
               "DoubleVar: " + DoubleVar + ";\n\n";
    }
}
