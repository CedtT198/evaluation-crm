package site.easy.to.build.crm.import_csv;

import site.easy.to.build.crm.import_csv.exception.CSVException;
import site.easy.to.build.crm.import_csv.parameter.CellCSV;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class ConstraintCSV {

    public static final LongPositive LONG_POSITIVE=new LongPositive();
    public static final IntPositive INT_POSITIVE=new IntPositive();
    public static final DoublePositive DOUBLE_POSITIVE=new DoublePositive();
    public static final LocalDateConstraint LOCALDATE=new LocalDateConstraint();
    public static final ListForeign LIST_FOREIGN = new ListForeign();
    public static final ListForeignDouble LIST_FOREIGN_DOUBLE = new ListForeignDouble();
    public static final TimeConstraint LOCAL_TIME = new TimeConstraint();
    public static final LocalDateTimeConstraint LOCALDATE_TIME = new LocalDateTimeConstraint();
    public static final StatusCorrect STATUS = new StatusCorrect();
    public static final BigDecimalPositive BIG_DECIMAL_POSITIVE = new BigDecimalPositive();

    public static class StatusCorrect implements CellCSV<String>{

        @Override
        public String getValue(String cell,int line) throws CSVException {
            try{
                String[] status = {"open", "assigned", "on-hold", "in-progress", "resolved", "closed", "reopened", "pending-customer-response", "escalated", "archived", "meeting-to-schedule", "assign-to-sales", "archived", "success"};
                for (String s : status) {   
                    if(cell.equalsIgnoreCase(s)){
                        return cell;
                    }
                }
                throw new CSVException("Type de status "+cell+" est inconnu sur la ligne "+line);
            }
            catch (NumberFormatException ex){
                throw new CSVException(ex.getMessage()+" sur la ligne "+line);
            }
        }
    }

    public static class LongPositive implements CellCSV<Long>{

        @Override
        public Long getValue(String cell,int line) throws CSVException {
            try{
                Long value=Long.parseLong(cell);
                if(value<0){
                    throw new CSVException("Valeur négative sur la ligne "+line);
                }
                return value;
            }
            catch (NumberFormatException ex){
                throw new CSVException(ex.getMessage()+" sur la ligne "+line);
            }
        }
    }

    public static class IntPositive implements CellCSV<Integer>{

        @Override
        public Integer getValue(String cell,int line) throws CSVException {
            try{
                Integer value=Integer.parseInt(cell);
                if(value<0){
                    throw new CSVException("Valeur négative sur la ligne "+line);
                }
                return value;
            }
            catch (NumberFormatException ex){
                throw new CSVException(ex.getMessage()+" sur la ligne "+line);
            }
        }
    }

    public static class TimeConstraint implements CellCSV<LocalTime>{

        @Override
        public LocalTime getValue(String cell,int line) throws CSVException {
            try{
                LocalTime value=LocalTime.parse(cell);
                return value;
            }
            catch (Exception ex){
                throw new CSVException(ex.getMessage()+" sur la ligne "+line);
            }
        }
    }

    public static class LocalDateTimeConstraint implements CellCSV<LocalDateTime>{

        @Override
        public LocalDateTime getValue(String cell,int line) throws CSVException {
            try{
                LocalDateTime value=LocalDateTime.parse(cell);
                return value;
            }
            catch (Exception ex){
                throw new CSVException(ex.getMessage()+" sur la ligne "+line);
            }
        }
    }

    public static class DoublePositive implements CellCSV<Double>{

        @Override
        public Double getValue(String cell,int line) throws CSVException {
            try{
                cell = cell.replace(",", ".");
                Double value=Double.parseDouble(cell);
                System.out.println("double "+cell);
                if(value<0){
                    throw new CSVException("Valeur négative sur la ligne "+line);
                }
                return value;
            }
            catch (NumberFormatException ex){
                throw new CSVException(ex.getMessage()+" sur la ligne "+line);
            }
        }
    }
    
    public static class BigDecimalPositive implements CellCSV<BigDecimal>{

        @Override
        public BigDecimal getValue(String cell,int line) throws CSVException {
            System.out.println(cell);
            try{
                cell = cell.replace(",", ".");
                BigDecimal value = new BigDecimal(cell);
                System.out.println("big decimal "+cell);
                if(value.compareTo(BigDecimal.ZERO) == -1){
                    throw new CSVException("Valeur négative sur la ligne "+line);
                }
                return value;
            }
            catch (NumberFormatException ex){
                throw new CSVException(ex.getMessage()+" sur la ligne "+line);
            }
        }
    }

    public static class LocalDateConstraint implements CellCSV<LocalDate>{

        @Override
        public LocalDate getValue(String cell,int line) throws CSVException {
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate value=LocalDate.parse(cell,formatter);
                return value;
            }
            catch (Exception ex){
                throw new CSVException(ex.getMessage()+" sur la ligne "+line);
            }
        }
    }

    public static class ListForeign implements CellCSV<List<String>>{

        @Override
        public List<String> getValue(String cell,int line) throws CSVException {
            try{
                List<String> value=new ArrayList<>();
                String[] foreignKeySplitted=cell.split(",");
                for(String foreignKey:foreignKeySplitted){
                    value.add(foreignKey.replace(" ",""));
                }
                return value;
            }
            catch (DateTimeException ex){
                throw new CSVException(ex.getMessage()+" sur la ligne "+line);
            }
        }
    }
    
    public static class ListForeignDouble implements CellCSV<List<Double>>{

        @Override
        public List<Double> getValue(String cell,int line) throws CSVException {
            try{
                List<Double> value=new ArrayList<>();
                String[] foreignKeySplitted=cell.split(",");
                for(String foreignKey:foreignKeySplitted){
                    Double d = Double.parseDouble(foreignKey);
                    value.add(d);
                }
                return value;
            }
            catch (DateTimeException ex){
                throw new CSVException(ex.getMessage()+" sur la ligne "+line);
            }
        }
    }
}
