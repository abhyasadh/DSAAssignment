package Nine.view;

import java.awt.*;

public class VerticalLayout implements LayoutManager{

    /**
     * The horizontal alignment constant that designates centering. Also used to designate center anchoring.
     */
    public final static int CENTER=0;
    /**
     * The horizontal alignment constant that designates right justification.
     */
    public final static int RIGHT=1;

    /**
     * The horizontal alignment constant that designates stretching the component horizontally.
     */
    public final static int BOTH=3;

    /**
     * The anchoring constant that designates anchoring to the top of the display area
     */
    public final static int TOP=1;
    /**
     * The anchoring constant that designates anchoring to the bottom of the display area
     */
    private final int vGap; //the vertical vGap between components...defaults to 5
    private final int alignment; //LEFT, RIGHT, CENTER or BOTH...how the components are justified
    private final int anchor; //TOP, BOTTOM or CENTER ...where are the components positioned in an overlarge space

    /**
     * Constructs a VerticalLayout instance anchored to the top with the specified vGap and horizontal alignment
     *
     * @param vGap An int value indicating the vertical seperation of the components
     * @param alignment An int value which is one of <code>RIGHT, LEFT, CENTER, BOTH</code> for the horizontal alignment.
     */
    public VerticalLayout(int vGap,int alignment){
        this(vGap,alignment,TOP);
    }
    /**
     * Constructs a VerticalLayout instance with the specified vGap, horizontal alignment and anchoring
     *
     * @param vGap An int value indicating the vertical seperation of the components
     * @param alignment An int value which is one of <code>RIGHT, LEFT, CENTER, BOTH</code> for the horizontal alignment.
     * @param anchor An int value which is one of <code>TOP, BOTTOM, CENTER</code> indicating where the components are
     * to appear if the display area exceeds the minimum necessary.
     */
    public VerticalLayout(int vGap,int alignment,int anchor){
        this.vGap=vGap; this.alignment=alignment; this.anchor=anchor;
    }

    //----------------------------------------------------------------------------
    private Dimension layoutSize(Container parent){
        Dimension dim=new Dimension(0,0);
        Dimension d;
        synchronized(parent.getTreeLock()){
            int n=parent.getComponentCount();
            for(int i=0;i<n;i++){
                Component c=parent.getComponent(i);
                if(c.isVisible()){
                    d= c.getPreferredSize();
                    dim.width=Math.max(dim.width,d.width); dim.height+=d.height;
                    if(i>0)dim.height+=vGap;
                }
            }
        }
        Insets insets=parent.getInsets();
        dim.width+=insets.left+insets.right;
        dim.height+=insets.top+insets.bottom+vGap+vGap;
        return dim;
    }
//-----------------------------------------------------------------------------
    /**
     * Lays out the container.
     */
    public void layoutContainer(Container parent){
        Insets insets=parent.getInsets();
        synchronized(parent.getTreeLock()){
            int n=parent.getComponentCount();
            Dimension pd=parent.getSize(); int y=0;
//work out the total size
            for(int i=0;i<n;i++){
                Component c=parent.getComponent(i);
                Dimension d=c.getPreferredSize();
                y+=d.height+vGap;
            }
            y-=vGap; //otherwise there's a vGap too many
//Work out the anchor paint
            if(anchor==TOP)y=insets.top;
            else if(anchor==CENTER)y=(pd.height-y)/2;
            else y=pd.height-y-insets.bottom;
//do layout
            for(int i=0;i<n;i++){
                Component c=parent.getComponent(i);
                Dimension d=c.getPreferredSize();
                int x=insets.left; int wid=d.width;
                if(alignment==CENTER)x=(pd.width-d.width)/2;
                else if(alignment==RIGHT)x=pd.width-d.width-insets.right;
                else if(alignment==BOTH)wid=pd.width-insets.left-insets.right;
                c.setBounds(x+23,y+23,wid,d.height);
                y+=d.height+vGap;
            }
        }
    }
    //-----------------------------------------------------------------------------
    public Dimension minimumLayoutSize(Container parent){return layoutSize(parent);}
    //-----------------------------------------------------------------------------
    public Dimension preferredLayoutSize(Container parent){return layoutSize(parent);}
//----------------------------------------------------------------------------
    /**
     * Not used by this class
     */
    public void addLayoutComponent(String name,Component comp){}
//-----------------------------------------------------------------------------
    /**
     * Not used by this class
     */
    public void removeLayoutComponent(Component comp){}
    //-----------------------------------------------------------------------------
    public String toString(){return getClass().getName()+"[vGap="+vGap+" align="+alignment+" anchor="+anchor+"]";}
}

