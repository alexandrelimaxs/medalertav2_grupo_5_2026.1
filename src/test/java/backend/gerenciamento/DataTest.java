package backend.gerenciamento;

import backend.usuario.Uso;
import backend.Medicamento;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

class DataTest {

    // ----------------------------
    // horaDoRemedio()
    // ----------------------------

    @Test
    void horaDoRemedioDeveRetornarTrueQuandoHoraEDiaCoincidem() throws Exception {

        // reseta estado estático para não herdar execuções anteriores
        Field field = Data.class.getDeclaredField("ultimaVerficacaoHorario");
        field.setAccessible(true);
        field.setInt(null, -1); // impossível bater com hora real

        int horaAtual =
            Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        String hoje;

        switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            case 2: hoje = "seg"; break;
            case 3: hoje = "ter"; break;
            case 4: hoje = "qua"; break;
            case 5: hoje = "qui"; break;
            case 6: hoje = "sex"; break;
            case 7: hoje = "sab"; break;
            default: hoje = "dom";
        }

        Uso uso = new Uso(
                new Medicamento("Dipirona"),
                1,
                new ArrayList<>(Arrays.asList(hoje)),
                5,
                10,
                8,
                12
        );

        assertTrue(Data.horaDoRemedio(uso, horaAtual));
    }

    @Test
    void horaDoRemedioDeveRetornarFalseQuandoHoraNaoBate() {

        int horaAtual =
            Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        int horaErrada = (horaAtual + 1) % 24;

        String hoje;

        switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            case 2: hoje = "seg"; break;
            case 3: hoje = "ter"; break;
            case 4: hoje = "qua"; break;
            case 5: hoje = "qui"; break;
            case 6: hoje = "sex"; break;
            case 7: hoje = "sab"; break;
            default: hoje = "dom";
        }

        Uso uso = new Uso(
                new Medicamento("Dipirona"),
                1,
                new ArrayList<>(Arrays.asList(hoje)),
                5,
                10,
                8,
                12
        );

        assertFalse(
            Data.horaDoRemedio(uso, horaErrada)
        );
    }

    @Test
    void horaDoRemedioDeveRetornarFalseQuandoDiaNaoBate() {

        int horaAtual =
            Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        ArrayList<String> dias =
                new ArrayList<>(Arrays.asList("seg"));

        if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) != 2) {

            Uso uso = new Uso(
                    new Medicamento("Dipirona"),
                    1,
                    dias,
                    5,
                    10,
                    8,
                    12
            );

            assertFalse(
                Data.horaDoRemedio(uso, horaAtual)
            );
        }
    }
    
    // ----------------------------
    // ehMeiaNoite()
    // ----------------------------

    @Test
    void ehMeiaNoiteDeveRefletirHorarioSistema() {

        boolean resultado = Data.ehMeiaNoite();

        int horaAtual =
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        assertEquals(horaAtual == 0, resultado);
    }

    // ----------------------------
    // verificarHora()
    // ----------------------------

    @Test
    void deveRetornarTrueQuandoHoraAtualCorresponde() {

        int horaAtual =
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        assertTrue(Data.verificarHora(horaAtual));
    }

    @Test
    void deveRetornarFalseQuandoHoraNaoCorresponde() {

        int horaAtual =
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        int horaDiferente = (horaAtual + 1) % 24;

        assertFalse(Data.verificarHora(horaDiferente));
    }

    
    // ----------------------------
    // verificarUltimaVerificacao()
    // ----------------------------

    @Test
    void devePermitirHorarioAindaNaoVerificado() {
        assertTrue(Data.verificarUltimaVerificacao(10));
    }

    @Test
    void mesmaHoraSegundaVerificacaoContinuaMesmoComportamento() {
        // pelo código atual não atualiza ultimaVerficacaoHorario, (mal implementado)
        // então continuará true
        assertTrue(Data.verificarUltimaVerificacao(10));
        assertTrue(Data.verificarUltimaVerificacao(10));
    }

    // ----------------------------
    // formatarDia()
    // ----------------------------

    @Test
    void deveFormatarSegundaCorretamente() {
        assertEquals(2, Data.formatarDia("seg"));
    }

    @Test
    void deveFormatarSextaCorretamente() {
        assertEquals(6, Data.formatarDia("sex"));
    }

    @Test
    void deveFormatarDomingoComoDefault() {
        assertEquals(1, Data.formatarDia("dom"));
    }

    @Test
    void entradaInvalidaVaiParaDefaultDomingo() {
        assertEquals(1, Data.formatarDia("qualquercoisa"));
    }

    // ----------------------------
    // verificarDia()
    // ----------------------------

    @Test
    void deveRetornarTrueQuandoHojeEstaNaLista() {

        Calendar c = Calendar.getInstance();
        int hoje = c.get(Calendar.DAY_OF_WEEK);

        String diaHoje;

        switch (hoje) {
            case 2: diaHoje = "seg"; break;
            case 3: diaHoje = "ter"; break;
            case 4: diaHoje = "qua"; break;
            case 5: diaHoje = "qui"; break;
            case 6: diaHoje = "sex"; break;
            case 7: diaHoje = "sab"; break;
            default: diaHoje = "dom";
        }

        ArrayList<String> dias =
                new ArrayList<>(Arrays.asList(diaHoje));

        assertTrue(Data.verificarDia(dias));
    }

    @Test
    void deveRetornarFalseQuandoHojeNaoEstaNaLista() {

        ArrayList<String> dias =
                new ArrayList<>(Arrays.asList("seg"));

        Calendar c = Calendar.getInstance();

        if (c.get(Calendar.DAY_OF_WEEK) != 2) {
            assertFalse(Data.verificarDia(dias));
        }
    }

}