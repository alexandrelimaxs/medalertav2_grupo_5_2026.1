package backend.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import backend.Medicamento;

class UsoTest {

    @Test
    void setDose_deveLancarExcecaoQuandoValorForNegativo() {
        Uso uso = novoUsoBase();

        assertThrows(IllegalArgumentException.class, () -> uso.setDose(-1));
    }

    @Test
    void setDuracaoDoTratamento_deveLancarExcecaoQuandoValorForNegativo() {
        Uso uso = novoUsoBase();

        assertThrows(IllegalArgumentException.class, () -> uso.setDuracaoDoTratamento(-10));
    }

    @Test
    void setQtdDisponivel_deveLancarExcecaoQuandoValorForNegativo() {
        Uso uso = novoUsoBase();

        assertThrows(IllegalArgumentException.class, () -> uso.setQtdDisponivel(-5));
    }

    @Test
    void setTipoDoRemedio_deveLancarExcecaoQuandoStringForVazia() {
        Uso uso = novoUsoBase();

        assertThrows(IllegalArgumentException.class, () -> uso.setTipoDoRemedio(""));
    }

    @Test
    void horariosToString_e_horariosStringToList_devemConverterCorretamente() {
        Uso uso = novoUsoBase();

        String horariosString = uso.horariosToString();
        ArrayList<String> horariosConvertidos = Uso.horariosStringToList(horariosString);

        assertEquals("08:00/12:00/20:00", horariosString);
        assertEquals(Arrays.asList("08:00", "12:00", "20:00"), horariosConvertidos);
    }

    @Test
    void stringToUso_deveRecuperarCamposPrincipais() {
        Uso usoOriginal = new Uso(
                new Medicamento("Dipirona"),
                2,
                new ArrayList<>(Arrays.asList("08:00", "16:00")),
                7,
                30,
                8,
                8);

        String usoString = usoOriginal.toString();
        Uso usoConvertido = Uso.stringToUso(usoString);

        assertEquals("Dipirona", usoConvertido.getRemedio().getNome());
        assertEquals(2, usoConvertido.getDose());
        assertEquals(Arrays.asList("08:00", "16:00"), usoConvertido.getHorarios());
        assertEquals(7, usoConvertido.getDuracaoDoTratamento());
        assertEquals(30, usoConvertido.getQtdDisponivel());
        assertEquals(8, usoConvertido.getHorarioDeInicio());
        assertEquals(8, usoConvertido.getIntervalo());
    }

    @Test
    void calcularHorariosDeUso_deveAdicionarApenasHoraInicialQuandoIntervaloForZero() {
        Uso uso = new Uso(
                new Medicamento("Paracetamol"),
                1,
                new ArrayList<>(Arrays.asList("09:00")),
                5,
                10,
                9,
                0);

        uso.calcularHorariosDeUso();

        assertEquals(1, uso.getHorariosDeUso().size());
        assertEquals(9, uso.getHorariosDeUso().get(0));
    }

    @Test
    void calcularHorariosDeUso_deveCalcularQuantidadeComBaseNoIntervalo() {
        Uso uso = new Uso(
                new Medicamento("Amoxicilina"),
                1,
                new ArrayList<>(Arrays.asList("06:00")),
                5,
                20,
                6,
                8);

        uso.calcularHorariosDeUso();

        assertEquals(Arrays.asList(6, 14, 22), uso.getHorariosDeUso());
    }

    @Test
    void toString_deveConterDadosSeparadosPorVirgula() {
        Uso uso = novoUsoBase();

        String usoString = uso.toString();

        assertTrue(usoString.contains("Paracetamol"));
        assertTrue(usoString.contains("08:00/12:00/20:00"));
        assertFalse(usoString.isEmpty());
    }

    private Uso novoUsoBase() {
        return new Uso(
                new Medicamento("Paracetamol"),
                1,
                new ArrayList<>(Arrays.asList("08:00", "12:00", "20:00")),
                5,
                20,
                8,
                12);
    }
}
