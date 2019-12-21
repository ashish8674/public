/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptoandload;

import cryptoandload.*;
import java.awt.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ashis
 */

 

//    public void add(Button et) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
public class DownloadFiles{
    
    
    //These variables are used in Actionperformed in for loop
    
    String linkto,copylink,del;   
    public void setDel(String del){
        this.del = del;
    }
    public String getDel(){
        return del;
    }
    public void setLinkto(String y){
        this.linkto = linkto;
    }
    public String getLink(){
        return linkto;
    }
        public void setCopylink(String copylink){
        this.copylink = copylink;
    }
    public String getCopylink(){
        return copylink;
    }
    
    
    //Constructor starts from here
    
    
    DownloadFiles() throws IOException{
        JFrame f = new JFrame("Internet files");
        JPanel panel = new JPanel(); 
       // f.setLayout(new BoxLayout(f,BoxLayout.Y_AXIS));
        
        //PopupMenu 
        JPopupMenu popup = new JPopupMenu("Options");
        //Popup items
        JMenuItem download = new JMenuItem("Download");
        JMenuItem sharelink = new JMenuItem("Get shareable link");
        JMenuItem deleteFile = new JMenuItem("Delete");
        
        
        //Action to Download file
                   download.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fc = new JFileChooser();
        int status = fc.showSaveDialog(fc);
        if(status==JFileChooser.APPROVE_OPTION){
            Download(getCopylink(),fc.getSelectedFile().getAbsolutePath());
            JOptionPane.showMessageDialog(null, "File downloaded successfully");
        }
    }
});
              //Action to share Link     
                   
    sharelink.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        
       StringSelection stringSelection = new StringSelection(getCopylink());
Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
clipboard.setContents(stringSelection, null);
JOptionPane.showMessageDialog(panel, "Link copied to clipboard");
    }
});
    
    
         //Action to delete file

         
    deleteFile.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        try {
            deleteT(getDel());
            System.out.println(getDel());
            DownloadFiles df = new DownloadFiles();
            f.setVisible(false);
            f.repaint();

            // SwingUtilities.updateComponentTreeUI(f);
        } catch (IOException ex) {
            Logger.getLogger(DownloadFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});
    
                                      
 
        popup.add(download);
        popup.add(sharelink);
        popup.add(deleteFile);
        Button bt ;//= new Button[5];
      Document doc = Jsoup.connect("http://35.237.202.227/files").get();
        Elements links = doc.select("a[href]:contains(.)");
        JLabel et;
        String icont =null;
        for (Element link : links) {
             final String linkaddress=link.attr("abs:href");
                  String extension = linkaddress.substring(linkaddress.lastIndexOf("."));
           // System.out.println(link.attr("abs:href")+">>"+link.text());
           switch(extension){
               case ".txt":{
                   icont = "txticon.png";
                   break;
               }
               case ".jpg":{
                   icont = "jpgicon.png";
                   break;
               }
               case ".png":{
                   icont = "pngicon.png";
                   break;
               }
               case ".java":{
                   icont = "javaicon.png";
                   break;
               }
               case ".html":{
                   icont = "htmlicon.png";
                   break;
               }
               case ".pdf":{
                   icont = "pdficon.png";
                   break;
               }
               case ".exe":{
                   icont = "exeicon.png";
                   break;
               }
               case ".zip":{
                   icont = "zipicon.png";
                   break;
               }
                case ".tar":{
                   icont = "zipicon.png";
                   break;
               }
                 case ".gz":{
                   icont = "zipicon.png";
                   break;
               }
                 case ".jpeg":{
                   icont = "zipicon.png";
                   break;
               }
           }
            ImageIcon imageIcon = new ImageIcon("C:/Users/Ashis/Pictures/icons/"+icont);
                 
                   // String contentType = new URL(link.attr("abs:href")).openConnection().;
                   
                   //Prints the all icons
                   // System.out.println(icont);
            et = new JLabel(""+link.text(),imageIcon,JLabel.CENTER);
//            et.addActionListener(new java.awt.event.ActionListener() {
//    public void actionPerformed(java.awt.event.ActionEvent evt) {
//        
//        System.out.println("You Pressed"+rt);
//    }
//});

 
et.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setDel(link.text());
                setLinkto(link.text());
                setCopylink(linkaddress);
                popup.show(f,evt.getXOnScreen(), evt.getYOnScreen());
            }
        });
            panel.add(et);
            
            
           // print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
      
       
     //  int a[] = null;
//        for(i = 0;i<=5;i++){
//            final int rt=i;
//            bt = new Button(""+i);
//            bt.addActionListener(new java.awt.event.ActionListener() {
//    public void actionPerformed(java.awt.event.ActionEvent evt) {
//        System.out.println("You Pressed"+rt);
//    }
//});
//             add(bt);
//        }
//        bt.addActionListener(new java.awt.event.ActionListener() {
//    public void actionPerformed(java.awt.event.ActionEvent evt) {
//        System.out.println("Hello");
//    }
//});
    
  
        // Creating Object of "boxlayout" in  
        // X_Axis from left to right 
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS); 
  
        // to set the box layout 
        panel.setLayout(boxlayout); 
//f.setLayout(new BoxLayout(f,BoxLayout.Y_AXIS));
    f.add(popup);
    f.add(panel);
    f.setBackground(Color.yellow);
    f.setTitle("Internet Files");
    f.setResizable(false);
       f.setSize(700, 700);
        f.setVisible(true);
        f.repaint();
        f.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
       
    }
    
    public void refreshT(){
       
    }
    public void Download(String linkaddr,String fileaddr){
        try {
            URL url = new URL(linkaddr);
            ReadableByteChannel readchannel = Channels.newChannel(url.openStream());
            FileOutputStream foutstream = new FileOutputStream(fileaddr);
            foutstream.getChannel().transferFrom(readchannel, 0, Long.MAX_VALUE);
            foutstream.close();
        } catch (MalformedURLException ex) {
           JOptionPane.showMessageDialog(null, "URL based problem") ;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Cannot download file, check your Internet connection");
        }
    }
    public void deleteT(String fileName){
        try {
            InputStream  ins ;
            BufferedReader br ;
            URL url = new URL("http://35.237.202.227/del.php?fileTodelete="+fileName);
            ins = url.openStream();
            br = new BufferedReader(new InputStreamReader(ins));
            String line;
            while((line = br.readLine())!=null){
                System.out.println(line);
            } } catch (MalformedURLException ex) {
            Logger.getLogger(DownloadFiles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DownloadFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void main(String args[]){
        try {
            new DownloadFiles();
            // fr.addWindowListener(new WindowAdapta);
//       Panel controlPanel = new Panel();
//      controlPanel.setLayout(new FlowLayout())
//      fr.add(controlPane
        } catch (IOException ex) {
            Logger.getLogger(DownloadFiles.class.getName()).log(Level.SEVERE, null, ex);
        } 
     
    }
    
}
