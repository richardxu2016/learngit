package com.soufun.java.source;

public class BSTree<T extends Comparable<T>> {

	private BSTNode<T> mRoot;

	// 节点类
	@SuppressWarnings("hiding")
	public class BSTNode<T extends Comparable<T>> {
		T key;
		BSTNode<T> left;
		BSTNode<T> right;
		BSTNode<T> parent; // 父节点

		public BSTNode(T key, BSTNode<T> left, BSTNode<T> right,
				BSTNode<T> parent) {
			this.key = key;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}

		public T getKey() {
			return key;
		}

		@Override
		public String toString() {
			return "BSTNode [key=" + key + "]";
		}

	}

	// 初始化树
	public BSTree() {
		mRoot = null;
	}

	// 前序遍历
	private void preOrder(BSTNode<T> treeNode) {
		if (treeNode != null) {
			System.out.print("key:" + treeNode.key+" ");
			preOrder(treeNode.left);
			preOrder(treeNode.right);
		}
	}

	public void preOrder() {
		preOrder(mRoot);
	}

	// 中序遍历
	private void inOrder(BSTNode<T> treeNode) {
		if (treeNode != null) {
			inOrder(treeNode.left);
			System.out.print("key:" + treeNode.key+" ");
			inOrder(treeNode.right);
		}
	}

	public void inOrder() {
		inOrder(mRoot);
	}

	// 后序遍历
	private void postOrder(BSTNode<T> treeNode) {
		if (treeNode != null) {
			postOrder(treeNode.left);
			postOrder(treeNode.right);
			System.out.print("key:" + treeNode.key+" ");
		}
	}

	public void postOrder() {
		postOrder(mRoot);
	}

	/*
	 * 递归查找二叉树中键值为key的节点
	 */
	private BSTNode<T> search(BSTNode<T> x, T key) {
		if (x == null) {
			return x;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			return search(x.left, key);
		} else if (cmp > 0) {
			return search(x.right, key);
		} else {
			return x;
		}
	}

	public BSTNode<T> search(T key){
		return search(mRoot, key);
	}
	/*
	 * 非递归查找二叉树中键值为key的节点
	 */
	private BSTNode<T> loopSearch(BSTNode<T> x, T key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) {
				return search(x.left, key);
			} else if (cmp > 0) {
				return search(x.right, key);
			} else {
				return x;
			}
		}
		return x;
	}
	
	public BSTNode<T> loopSearch(T key){
		return loopSearch(mRoot, key);
	}
	
	//查找最小节点
	private BSTNode<T> minimum(BSTNode<T> treeNode){
		if (treeNode==null) {
			return null;
		}
		while(treeNode.left!=null){
			treeNode = treeNode.left;
		}
		return treeNode;
	}
	
	public T minimum(){
		BSTNode<T> p = minimum(mRoot);
		if (p!=null) {
			return p.key;
		} else {
            return null;
		}
	}
	
	//查找最大节点
	private BSTNode<T> maxinum(BSTNode<T> treeNode){
		if (treeNode!=null) {
			while(treeNode.right!=null)
				treeNode = treeNode.right;
			return treeNode ;
		}
		return null;
	}
	
	public T maxinum(){
		BSTNode<T> p = maxinum(mRoot);
		if (p!=null) {
			return p.key;
		}
		return null;
	}
	
	//找到该节点的后继节点，即二叉树中数值大于该节点的最小节点
	public BSTNode<T> successor(BSTNode<T> x){
		//如果x存在右孩子，则“x的后继节点”是“以其右孩子为根的子树的最小节点”
		if (x.right!=null) {
			return minimum(x.right);
		}else{
			/*
			 * x没有右孩子
			 * 1、x为左孩子，则x的后继节点是它的父节点
			 * 2、x为右孩子，则查找“x的最低的祖先结点，并且该祖先结点要作为左孩子存在”，
			 *    该祖先节点的父节点即是x的后继节点
			 */
			BSTNode<T> y = x.parent;
			while((y!=null) && (x==y.right)){
                x = y;
                y = y.parent;
		    }
			return y ;
	   }
    }
	
	/*
	 * 找到该节点的前驱节点 即二叉树中数值小于该数的最大节点
	 */
	public BSTNode<T> predecessor(BSTNode<T> x) {
		//如果存在左子树，就是左子树的最大节点
		if (x.left!=null) {
			return maxinum(x.left);
		}
		/*
		 * 没有左孩子
		 * 1、x为右孩子，x的前驱为其父节点。
		 * 2、x为左孩子，查找x的最低祖先节点，并且该祖先节点作为右孩子存在，
		 *    则该祖先节点的父节点就是x的前驱节点
		 */
		BSTNode<T> y = x.parent;
		while((y!=null)&&(x==y.left)){
			x = y ;
			y = y.parent;
		}
		return y;
	}
	
	//插入
	private void insert(BSTree<T> tree,BSTNode<T> node){
		
		int cmp ;
		BSTNode<T> y = null ;
		BSTNode<T> x = tree.mRoot;
		
		//查找插入位置
		while(x!=null){
			y = x ;
			cmp = node.key.compareTo(x.key);
			if (cmp<0) {
				x = x.left ;
			}else{
				x = x.right;
			}
		}
		
		node.parent = y ;
		if (y==null) {
			tree.mRoot = node ;
		}else{
			cmp = node.key.compareTo(y.key);
			if (cmp<0) {
				y.left = node ;
			}else{
				y.right = node ;
			}
		}
	}
	/**
	 * 可以插入相同的值
	 * @param key
	 */
	public void insert(T key){
		BSTNode<T> node = new BSTNode<T>(key,null,null,null);
		if (node!=null) {
			insert(this,node);
		}
	}
	
	//删除节点
	private BSTNode<T> remove(BSTree<T> tree,BSTNode<T> node){
		BSTNode<T> x = null ;
		BSTNode<T> y = null ;  //后继节点
		if (node.left==null || node.right == null) {
			y = node ;
		} else {
            y = successor(node);  
		}
		if (y.left != null) {
			x = y.left;
		} else {
            x = y.right;
		}
		if (x!=null) {
			x.parent = y.parent ;
		} 
		if (y.parent == null) {
			tree.mRoot = x;
		} else if(y == y.parent.left){
			y.parent.left = x ;
		}else{
			y.parent.right = x ;
		}
		if (y!=node) {
			node.key = y.key;
		}
		return y;
	}
	
	//待定
	public void remove(T key){
		 BSTNode<T> z, node; 
         if ((z = search(mRoot, key)) != null)
		        if ( (node = remove(this, z)) != null)
		            node = null;
	}
	
	//销毁
	private void destory(BSTNode<T> node){
		if (node==null) {
			return ;
		}
		if (node.left !=null) 
			destory(node.left);
		if (node.right !=null) 
			destory(node.right);
		node  = null ;
	}
	
	public void clear(){
		destory(mRoot);
		mRoot = null ;
	}
	
	//打印二叉树
	private void print(BSTNode<T> node, T key, int direction){
		if (node!=null) {
			if (direction==0) { //node根节点
				System.out.print("root:"+node.key+"\n");
			}else{
				System.out.printf("%2d is %2d's %6s child\n", node.key, key, 
						direction==1?"right" : "left");
			}
			 print(node.left, node.key, -1);
			 print(node.right,node.key,  1);
		}
	}
	
	public void print() {
	     if (mRoot != null)
		           print(mRoot, mRoot.key, 0);
	}
	
	
	private static final int arr[] = {1,5,4,3,2,6,9,8,7,10,16,12,13,11};
	public static void main(String[] args) {
		 int i, ilen;
	        BSTree<Integer> tree=new BSTree<Integer>();

	        System.out.print("== 依次添加: ");
	        ilen = arr.length;
	        for(i=0; i<ilen; i++) {
	            System.out.print(arr[i]+" ");
	            tree.insert(arr[i]);
	        }

	        System.out.print("\n== 前序遍历: \n");
	        tree.preOrder();

	        System.out.print("\n== 中序遍历: \n");
	        tree.inOrder();

	        System.out.print("\n== 后序遍历: \n");
	        tree.postOrder();
	        System.out.println();

	        System.out.println("== 最小值: "+ tree.minimum());
	        System.out.println("== 最大值: "+ tree.maxinum());
	        System.out.println("== 树的详细信息: ");
	        tree.print();

	        System.out.print("\n== 删除根节点: "+ arr[3]);
	        tree.remove(arr[3]);

	        System.out.print("\n== 中序遍历: ");
	        tree.inOrder();
	        System.out.println();

	        // 销毁二叉树
	        tree.clear();
	}
}
