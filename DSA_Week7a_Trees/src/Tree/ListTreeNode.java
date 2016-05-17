package Tree;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Naomi
 */
public class ListTreeNode<E> implements MutableTreeNode
{
    private E element;
    private MutableTreeNode parent;
    private List<MutableTreeNode> children;
    
    public ListTreeNode()
    {
        this(null);
    }
    
    public ListTreeNode(E element)
    {
        this.element = element;
        parent = null;
        children = new ArrayList<MutableTreeNode>();//no initial children
    }
    
    //return an Enumeration of the child nodes
    @Override
    public Enumeration<E> children()
    {
        return (Enumeration<E>) (new Enumerator(children.iterator()));
    }
    
    //returns that this node allows children
    @Override
    public boolean getAllowsChildren() 
    {
        return true;
    }
    
    //returns the child at specified index
    @Override
    public TreeNode getChildAt(int childIndex) throws IndexOutOfBoundsException
    {
        return children.get(childIndex);
    }
    
    //returns the number of children of this node
    @Override
    public int getChildCount() 
    {
        return children.size();
    }
    
    //returns the index of node or -1 if node not found
    @Override
    public int getIndex(TreeNode node) 
    {
        if(node==null)
            throw new IllegalArgumentException();
        else
            return children.indexOf(node);
    }
    
    //return the parent node or null if this node is the root
    @Override
    public TreeNode getParent() 
    {
        return parent;
    }
    
    //return whether this node is a leaf
    @Override
    public boolean isLeaf()
    {
        return (getChildCount()==0);
    }
    
    //add the child node at specified index, updating this node
    //add child node to reflect the change
    @Override
    public void insert(MutableTreeNode child, int index) throws IllegalArgumentException
    {
        if(child==null)
            throw new IllegalArgumentException("null child");
        child.removeFromParent();//remove child from its existing parent
        children.add(index, child);//update list of child nodes
        child.setParent(this);//update the child node
    }

    //removes the child at index from this node, updating this
    //node and child node to reflect the change
    @Override
    public void remove(int index) 
    {
        MutableTreeNode child = children.get(index);//update list
        if(child!=null)
            remove(child);
    }

    //remove the specified child from this node, updating this
    //node and child node to reflect the change
    @Override
    public void remove(MutableTreeNode node) 
    {
        if(children.remove(node))//node found and removed
            node.setParent(null);
    }

    //remove this node from its parent, updating this
    //node and its parent node
    @Override
    public void removeFromParent() 
    {
        if(this.parent!=null)
        {
            parent.remove(this);
            this.parent = null;
        }
    }
    
    //sets the paretn of this node to be newParent
    //but does not update newParent
    @Override
    public void setParent(MutableTreeNode newParent) 
    {
        removeFromParent();//remove this node from its existing parent
        this.parent = newParent;
    }

    //set the element held in this node
    @Override
    public void setUserObject(Object object) 
    {
        this.element = (E)object;//unchecked, could throw exception
    }

    public E getUsrObject()
    {
        return element;
    }
    
    public static void main(String[] args)
    {
        
    }
    
    //utility class to wrap an Iterator object as an Enumeration object
    class Enumerator<E> implements Enumeration<E>
    {
        private Iterator<E> iterator;
        
        public Enumerator(Iterator<E> iterator)
        {
            this.iterator = iterator;
        }
        
        @Override
        public boolean hasMoreElements() 
        {
            return iterator.hasNext();
        }

        @Override
        public E nextElement() 
        {
            return iterator.next();
        }
    }
}
