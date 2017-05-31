import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import static javax.swing.Action.SHORT_DESCRIPTION;
        
public class BotaoBarra extends AbstractAction implements ActionListener {
    
    public BotaoBarra(int id, ImageIcon icone, String desc){
    
       super(desc, icone);
       this.putValue("id", id);
       this.putValue(SHORT_DESCRIPTION, desc);
    }
    public void actionPerformed(ActionEvent evt){
        
        if((int) getValue("id")==0){
        ClientesView.limparCampos();
        ClientesView.desbloquearCampos(enabled);
        }else if((int) getValue("id")==3){
            JOptionPane.showMessageDialog(null, "teste");
            String busca = JOptionPane.showInputDialog("Entre com o Codigo do Cliente");
            
            ClientesView.executaConsulta(busca);
        }
        
    }
}