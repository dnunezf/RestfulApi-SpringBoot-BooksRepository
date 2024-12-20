package com.david.database.mappers;

/*ENCAPSULATES ALL OF MAPPING LOGIC FOR OUR APP
 * WE CAN IMPLEMENT THIS INTERFACE WITH BEANS, AND WE CAN
 * INJECT THOSE BEANS WHEN THEY ARE NEEDED*/
public interface Mapper<A, B>{
    B mapTo(A a);
    A mapFrom(B b);
}
