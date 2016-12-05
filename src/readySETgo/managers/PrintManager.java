package readySETgo.managers;

import javax.print.PrintService;
import javax.swing.JOptionPane;

import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 * 
 * Manager for printing the stage
 * 
 * @author ReadySETgo
 * @version Beta 3
 * @since 2016-12-04
 * 
 */
public class PrintManager {
	
    private Printable printable;
    private static PrintManager manager = new PrintManager();

    // Disable default constructor
    private PrintManager() {}
    
    /**
     * Registers a printable for later printing
     * @param printable The Printable to register
     */
    public static void register(Printable printable) { manager.printable = printable; }

    /**
     * Shows the system print dialog for printing the registered Printable
     */
    public static void print() {
        if (manager.printable != null) {
            PrintService[] services = PrinterJob.lookupPrintServices();
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(manager.printable);
            try {
                if (services != null && services.length > 0) { job.setPrintService(services[0]); }
                boolean ok = job.printDialog();
                if (ok) { job.print(); }
            }
            catch (PrinterException e) { 
            	JOptionPane.showMessageDialog(ComponentManager.getComp("MainFrame"), "There was an error printing: \r\n" + e.getMessage(), "Error printing.", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

