package readySETgo;

import javax.swing.*;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 * Created by Ksenia Belikova on 11/26/16.
 */
public class PrintManager {
    private Printable printable;
    private static PrintManager manager = new PrintManager();

    public static PrintManager getManager() {
        return manager;
    }

    public void register(Printable printable) {
        this.printable = printable;
    }

    public void print() {
        if (printable != null) {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(printable);
            boolean ok = job.printDialog();
            if (ok) {
                try {
                    job.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
              /* The job did not successfully complete */
                }
            }
        }
    }
}
