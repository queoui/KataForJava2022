package edu.pdx.cs410J.nmuller;

import org.junit.jupiter.api.Test;

public class KataTest
{

  @Test
  void canInstantiateKataClass() {
    new Kata();
  }

  @Test
  void ReturnsFoo(){
    Kata new_var = new Kata();
    assertThat(new_var.compute("30"),equalTo("FooBar"));
  }

}
