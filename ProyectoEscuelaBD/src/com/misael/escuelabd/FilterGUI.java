package com.misael.escuelabd;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FilterGUI extends JFrame {
    private       JButton           aplicarButton;
    private       JButton           cancelarButton;
    private       JCheckBox         activoCheckBox;
    private       JCheckBox         bachilleratoCheckBox;
    private       JCheckBox         femeninoCheckBox;
    private       JCheckBox         inactivoCheckBox;
    private       JCheckBox         masculinoCheckBox;
    private       JCheckBox         primariaCheckBox;
    private       JCheckBox         secundariaCheckBox;
    private       JComboBox<String> gradosComboBox;
    private       JLabel            estadoDelAlumnoLabel;
    private       JLabel            filtrarLabel;
    private       JLabel            gradoLabel;
    private       JLabel            géneroLabel;
    private       JLabel            nivelLabel;
    private       JPanel            panelMain;
    private       String            activeQuery = "";
    private       String            genderQuery = "";
    private       String            gradeQuery  = "";
    private       String            levelQuery  = "";
    private final MainGUI           main;
    public        String            fullQuery   = "";

    public FilterGUI(MainGUI main) {
        this.main = main;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panelMain);
        this.setUndecorated(true);
        initActionListeners();
        this.pack();
    }

    public void initActionListeners() {
        cancelarButton.addActionListener(e -> this.setVisible(false));

        aplicarButton.addActionListener(e -> {
            activeQuery = getActiveQuery();
            genderQuery = getGenderQuery();
            levelQuery  = getLevelQuery();
            gradeQuery  = getGradeQuery();

            fullQuery = activeQuery + genderQuery + levelQuery + gradeQuery;

            main.tableAlumnos.setModel(main.conectar.fillTable("SELECT alumno.id_alumno, alumno.matricula, alumno.nombre, grado.nivel, grado.grado, alumno.genero, alumno.fecha_nacimiento, alumno.telefono, tutor.nombre \n" +
                                                                       "FROM alumno, tutor, alumno_tutor, grado, inscripcion\n" +
                                                                       "WHERE alumno.id_alumno = alumno_tutor.id_alumno AND alumno_tutor.id_tutor = tutor.id_tutor AND inscripcion.id_alumno = alumno.id_alumno AND grado.id_grado = inscripcion.id_grado\n" +
                                                                       fullQuery + ";"));

        });

    }

    private String getGradeQuery() {
        if (gradosComboBox.getSelectedIndex() > 0) {
            return "AND grado.grado = '" + gradosComboBox.getSelectedIndex() + "'";
        }
        return "";
    }

    private String getLevelQuery() {
        if (primariaCheckBox.isSelected() && !secundariaCheckBox.isSelected() && !bachilleratoCheckBox.isSelected()) {
            return " AND grado.nivel = 'Primaria'";
        } else if (secundariaCheckBox.isSelected() && !primariaCheckBox.isSelected() && !bachilleratoCheckBox.isSelected()) {
            return " AND grado.nivel = 'Secundaria'";
        } else if (bachilleratoCheckBox.isSelected() && !primariaCheckBox.isSelected() && !secundariaCheckBox.isSelected()) {
            return " AND grado.nivel = 'Bachillerato'";
        } else if (primariaCheckBox.isSelected() && secundariaCheckBox.isSelected() && bachilleratoCheckBox.isSelected()) {
            return "";
        }
        return "";
    }

    private String getGenderQuery() {
        if (masculinoCheckBox.isSelected() && !femeninoCheckBox.isSelected()) {
            return " AND alumno.genero = 1";
        } else if (femeninoCheckBox.isSelected() && !masculinoCheckBox.isSelected()) {
            return " AND alumno.genero = 2";
        } else if (masculinoCheckBox.isSelected() && femeninoCheckBox.isSelected()) {
            return "";
        }
        return "";
    }

    private String getActiveQuery() {
        if (activoCheckBox.isSelected() && !inactivoCheckBox.isSelected()) {
            return " AND alumno.activo = 1";
        } else if
        (inactivoCheckBox.isSelected() && !activoCheckBox.isSelected()) {
            return " AND alumno.activo = 0";
        } else if
        (activoCheckBox.isSelected() && inactivoCheckBox.isSelected()) {
            return "";
        }
        return "";
    }
}
