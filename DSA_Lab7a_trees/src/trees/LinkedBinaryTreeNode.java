package trees;

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
public class LinkedBinaryTreeNode<E> implements MutableTreeNode
{
    private E element;
    private MutableTreeNode parent;
    private MutableTreeNode leftChild;
    private MutableTreeNode rightChild;
    
    public LinkedBinaryTreeNode()
    {
        this(null);
    }
    
    public LinkedBinaryTreeNode(E element)
    {
        this.element = element;
        parent = null;
        leftChild = null;
        rightChild = null;
        //children = new ArrayList<MutableTreeNode>();//no initial children
    }
    
    // returns an Enumeration of the child nodes
    public Enumeration<MutableTreeNode> children()
    {
        List<MutableTreeNode> list = new ArrayList<MutableTreeNode>();
        if(rightChild != null)
            list.add(rightChild);
        if(leftChild != null)
            list.add(leftChild);
        return (Enumeration<MutableTreeNode>) (new Enumerator(list.iterator()));
    }
    
    // returns that this node allows children
    @Override
    public boolean getAllowsChildren() 
    {
        return true;
    }
    
    // return the child at specified index
    @Override
    public TreeNode getChildAt(int childIndex) throws IndexOutOfBoundsException
    {
        if(childIndex > 1 || childIndex < 0)
            throw new IndexOutOfBoundsException("NOT IN RANGE OF CHILDREN, 0 or 1");
        else if(childIndex == 1)
            return rightChild;
        else
            return leftChild;
    }
    
    // returns the number of children of this node
    @Override
    public int getChildCount()
    {
        int count = 0;
        if(leftChild != null)
            count++;
        if(rightChild != null)
            count++;
        return count;
    }
    
    // returns the index of node or -1 if node not found
    @Override
    public int getIndex(TreeNode node) 
    {
        if(node == null)
            throw new IllegalArgumentException();
        else if(leftChild != null && leftChild.equals(node))
            return 0;
        else if(rightChild != null && rightChild.equals(node))
            return 1;
        else
            return -1;
    }
    
    // return the parent node or null if this node is the root
    @Override
    public TreeNode getParent() 
    {
        return parent;
    }

    // return whether this node is a leaf
    @Override
    public boolean isLeaf() 
    {
        return (getChildCount() == 0);
    }
    
    // add the child node at specified index, updating this node
    // and child node to reflect the change
    @Override
    public void insert(MutableTreeNode child, int index) 
    {
        if(index == 0 && leftChild != null)
            throw new IllegalArgumentException("Already a child node There");
        else if(index == 1 && rightChild != null)
            throw new IllegalArgumentException("Already a child node There");
        else if(index == 0)
        {
            child.removeFromParent();
            leftChild = child;
            leftChild.setParent(this);
        }
        else if(index == 1)
        {
            child.removeFromParent();
            rightChild = child;
            rightChild.setParent(this);
        }
        else
            throw new IllegalArgumentException("Index out of bounds");
    }
    
    // removes the child at index from this node, updating this
    // node and child node to refelect the change
    @Override
    public void remove(int index) 
    {
        //MutableTreeNode child = children.get(index); // update list
        if(index == 0 && leftChild != null)
        {
            leftChild.setParent(null);
            leftChild = null;
        }
        else if(index == 1 && rightChild != null)
        {
            rightChild.setParent(null);
            rightChild = null;
        }
    }
    
    // removes the specified child from this node, updating this
    // node and child node to reflect the change
    @Override
    public void remove(MutableTreeNode node) 
    {
        if(node != null && node.equals(leftChild)) // node found and removed
        {
            node.setParent(null);
            leftChild = null;
        }
        else if(node != null && node.equals(rightChild)) // node found and removed
        {
            node.setParent(null);
            rightChild = null;
        }
    }
    
    // remove this node from its parent, updating this
    // node and its parent node
    @Override
    public void removeFromParent() 
    {
        if(this.parent != null)
        {
            parent.remove(this);
            this.parent = null;
        }
    }

    // sets the parent of this node to be newParent
    // but does not update newParent
    @Override
    public void setParent(MutableTreeNode newParent) 
    {
        //removeFromParent(); // remove this node from its existing parent
        this.parent = newParent;
    }
    
    // set the element held in this node
    @Override
    public void setUserObject(Object object) 
    {
        this.element = (E)object; // unchecked, could throw exception
    }

    public E getUserObject()
    {
        return element;
    }

    // return a string representation of the node and its child nodes
    // in preorder notation
    public String toString()
    {
        String output = "" + this.element;
        if(!isLeaf())
        {
            output += "[";
            output += "" + leftChild;
            output += "," + rightChild;
            output += "]";
        }
        return output;
    }
    
    public static void main(String[] args) 
    {
        // create some sample nodes
        MutableTreeNode root = new LinkedBinaryTreeNode<String>("A");
        MutableTreeNode nodeB = new LinkedBinaryTreeNode<String>("B");
        MutableTreeNode nodeC = new LinkedBinaryTreeNode<String>("C");
        MutableTreeNode nodeD = new LinkedBinaryTreeNode<String>("D");
        MutableTreeNode nodeE = new LinkedBinaryTreeNode<String>("E");
        MutableTreeNode nodeF = new LinkedBinaryTreeNode<String>("F");
        MutableTreeNode nodeG = new LinkedBinaryTreeNode<String>("G");
        // build the tree
        
        nodeB.insert(nodeD, 1);
        //nodeC.insert(nodeD, 1);
        nodeD.insert(nodeF, 0);
        nodeD.insert(nodeG, 1);
        
        root.insert(nodeB, 0);
        root.insert(nodeC, 1);
        nodeC.insert(nodeE, 0);
        
        System.out.println("Original Tree: " + root);
        System.out.println("Original Tree: 2nd Tier: " + nodeB + " and " + nodeC);
        
        root.remove(nodeC);
//        nodeB.insert(nodeC, 1);
        System.out.println("Modified Tree: " + root);
        System.out.println("Modified Tree: " + nodeC);
        nodeB.insert(nodeC, 0);
        System.out.println("Added to B: " + root);
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
