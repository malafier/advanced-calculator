module pl.edu.pw.ee {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.edu.pw.ee to javafx.fxml;
    exports pl.edu.pw.ee;
}
