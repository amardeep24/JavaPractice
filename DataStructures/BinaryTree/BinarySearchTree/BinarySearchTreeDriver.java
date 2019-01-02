public class BinarySearchTreeDriver {

    public static void main(String[] args) {
        BinarySearchTree bt = new BinarySearchTree();
        bt.add(10);
        bt.add(3);
        bt.add(67);
        bt.add(23);
        bt.add(34);
        bt.add(12);

        bt.printInOrder();

        System.out.println(BinarySearchTree.isBst(bt));

        bt.remove(23);

        bt.printInOrder();
    }
}
class BinarySearchTree{
    private Node root;

    public void add(int data){
        this.root = add(data, this.root);
    }

    private Node add(int data, Node root){
        if(root == null){
            root = new Node(data);
            return root;
        }
        if (data > root.data){
            root.right = add(data, root.right);
        }else if(data < root.data){
            root.left = add(data, root.left);
        }
        return root;
    }

    public void remove(int data){
        this.root = remove(data, this.root);
    }

    private Node remove(int data, Node root){
        if(root == null){
            return root;
        }
        if(data > root.data){
            root.right = remove(data, root.right);
        }
        else if(data < root.data){
            root.left = remove(data, root.left);
        }
        else{
            if(root.left != null && root.right != null){
                int min = findMinInRight(root.right);
                root.data = min;
                root.right = remove(min, root.right);
            }
            else if(root.left != null){
                root.data = root.left.data;
            }
            else if(root.right != null){
                root.data = root.right.data;
            }
            else{
                return null;
            }
        }
        return root;
    }

    private int findMinInRight(Node root){
       while(root.left != null){
           root = root.left;
       }
       return root.data;
    }

    public void printInOrder(){
        printInOrder(this.root);
    }

    private void printInOrder(Node root){
        if(root == null){
            return;
        }
        printInOrder(root.left);
        System.out.println(root.data);
        printInOrder(root.right);
    }

    public static boolean isBst(BinarySearchTree bt){
        return isBst(bt.root);
    }

    private static boolean isBst(Node root){

        if(root == null){
            return true;
        }
        if(root.left == null && root.right != null && root.right.data > root.data){
            return isBst(root.right);
        }
        else if(root.right == null & root.left != null && root.left.data < root.data){
            return isBst(root.left);
        }
        else if(root.right == null && root.left == null){
            return true;
        }
        else if(root.right != null && root.left != null){
            return isBst(root.left) && isBst(root.right);
        }
        return false;
    }



    private class Node{
        private int data;
        private Node left = null;
        private Node right = null;

        public Node(int data){
            this.data = data;
        }
    }
}
