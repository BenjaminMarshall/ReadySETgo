package readySETgo;

import javax.print.PrintService;
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
            PrintService[] services = PrinterJob.lookupPrintServices();
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(printable);
            try {
                if (services != null && services.length > 0) {
                    job.setPrintService(services[0]);
                }
                boolean ok = job.printDialog();
                if (ok) {
                    job.print();
                }
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
}

