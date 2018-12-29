package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Sala;

/**
 *
 * @author snow
 */
public class DataUtil {

    public static java.sql.Date stringToDateSQL(String data) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return new java.sql.Date(fmt.parse(data).getTime());
        } catch (ParseException ex) {
            //Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String dateSQLToString(java.sql.Date dateSQL) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateSQL);
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return fmt.format(calendar.getTime());
    }

    public static String dateToStringddMMyyy(Date data) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return fmt.format(data);
    }

    public static String dateToStringyyMMdd(Date data) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
        return fmt.format(data);
    }

    public static String dateToString(long mili) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mili);
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(calendar.getTime());
    }

    public static Calendar stringToCalendar(String string) throws ParseException {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        Date dateSimples = formatador.parse(string);
        Calendar dataCalendar = Calendar.getInstance();
        dataCalendar.setTime(dateSimples);
        return dataCalendar;
    }

    public static Calendar dateToCalendar(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(data.getTime());
        return calendar;
    }

    public static String stringToAge(String dtNasc) throws ParseException {
        int idd;
        Calendar nascimento = stringToCalendar(dtNasc);
        Calendar hoje = Calendar.getInstance();
        idd = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
        Calendar aniversário = (Calendar) nascimento.clone();
        aniversário.add(Calendar.YEAR, idd);
        if (hoje.before(aniversário)) {
            idd--;
        }
        return String.valueOf(idd);
    }

    public static String conflitoHorario2(Sala sl1, Sala sl2) {
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
        try {
            Calendar calHIni = Calendar.getInstance();
            calHIni.setTime(fmt.parse(sl1.getHorarioIni()));
            Calendar calHFim = Calendar.getInstance();
            calHFim.setTime(fmt.parse(sl1.getHorarioFim()));
            Calendar calIIni = Calendar.getInstance();
            calIIni.setTime(fmt.parse(sl2.getHorarioIni()));
            Calendar calIFim = Calendar.getInstance();
            calIFim.setTime(fmt.parse(sl2.getHorarioFim()));
            Date x = calHIni.getTime();
            Date y = calHFim.getTime();
            if (x.equals(y)) {
                return "Hora inicio igual a hora final.";
            }
            if (x.after(y)) {
                return "Hora inicio depois de hora final.";
            }
            if (sl1.getId().equals(sl2.getId()) && sl1.getDiaSemana().equals(sl2.getDiaSemana())) {
                if (x.equals(calIIni.getTime())) {
                    return "Iniciando no mesmo horario.";
                }
                if (y.equals(calIFim.getTime())) {
                    return "Terminando no mesmo horario.";
                }
                if (x.after(calIIni.getTime()) && x.before(calIFim.getTime()) && y.after(calIIni.getTime()) && y.before(calIFim.getTime())) {
                    return "Há conflitos com " + sl2.getId();
                }
                if (x.before(calIIni.getTime()) && y.after(calIFim.getTime())) {
                    return "Inicia antes e termina depois.";
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sem conflitos";
    }
}
