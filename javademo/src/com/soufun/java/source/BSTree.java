package com.soufun.java.source;

public class BSTree<T extends Comparable<T>> {

	private BSTNode<T> mRoot;

	// �ڵ���
	@SuppressWarnings("hiding")
	public class BSTNode<T extends Comparable<T>> {
		T key;
		BSTNode<T> left;
		BSTNode<T> right;
		BSTNode<T> parent; // ���ڵ�

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

	// ��ʼ����
	public BSTree() {
		mRoot = null;
	}

	// ǰ�����
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

	// �������
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

	// �������
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
	 * �ݹ���Ҷ������м�ֵΪkey�Ľڵ�
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
	 * �ǵݹ���Ҷ������м�ֵΪkey�Ľڵ�
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
	
	//������С�ڵ�
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
	
	//�������ڵ�
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
	
	//�ҵ��ýڵ�ĺ�̽ڵ㣬������������ֵ���ڸýڵ����С�ڵ�
	public BSTNode<T> successor(BSTNode<T> x){
		//���x�����Һ��ӣ���x�ĺ�̽ڵ㡱�ǡ������Һ���Ϊ������������С�ڵ㡱
		if (x.right!=null) {
			return minimum(x.right);
		}else{
			/*
			 * xû���Һ���
			 * 1��xΪ���ӣ���x�ĺ�̽ڵ������ĸ��ڵ�
			 * 2��xΪ�Һ��ӣ�����ҡ�x����͵����Ƚ�㣬���Ҹ����Ƚ��Ҫ��Ϊ���Ӵ��ڡ���
			 *    �����Ƚڵ�ĸ��ڵ㼴��x�ĺ�̽ڵ�
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
	 * �ҵ��ýڵ��ǰ���ڵ� ������������ֵС�ڸ��������ڵ�
	 */
	public BSTNode<T> predecessor(BSTNode<T> x) {
		//������������������������������ڵ�
		if (x.left!=null) {
			return maxinum(x.left);
		}
		/*
		 * û������
		 * 1��xΪ�Һ��ӣ�x��ǰ��Ϊ�丸�ڵ㡣
		 * 2��xΪ���ӣ�����x��������Ƚڵ㣬���Ҹ����Ƚڵ���Ϊ�Һ��Ӵ��ڣ�
		 *    ������Ƚڵ�ĸ��ڵ����x��ǰ���ڵ�
		 */
		BSTNode<T> y = x.parent;
		while((y!=null)&&(x==y.left)){
			x = y ;
			y = y.parent;
		}
		return y;
	}
	
	//����
	private void insert(BSTree<T> tree,BSTNode<T> node){
		
		int cmp ;
		BSTNode<T> y = null ;
		BSTNode<T> x = tree.mRoot;
		
		//���Ҳ���λ��
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
	 * ���Բ�����ͬ��ֵ
	 * @param key
	 */
	public void insert(T key){
		BSTNode<T> node = new BSTNode<T>(key,null,null,null);
		if (node!=null) {
			insert(this,node);
		}
	}
	
	//ɾ���ڵ�
	private BSTNode<T> remove(BSTree<T> tree,BSTNode<T> node){
		BSTNode<T> x = null ;
		BSTNode<T> y = null ;  //��̽ڵ�
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
	
	//����
	public void remove(T key){
		 BSTNode<T> z, node; 
         if ((z = search(mRoot, key)) != null)
		        if ( (node = remove(this, z)) != null)
		            node = null;
	}
	
	//����
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
	
	//��ӡ������
	private void print(BSTNode<T> node, T key, int direction){
		if (node!=null) {
			if (direction==0) { //node���ڵ�
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

	        System.out.print("== �������: ");
	        ilen = arr.length;
	        for(i=0; i<ilen; i++) {
	            System.out.print(arr[i]+" ");
	            tree.insert(arr[i]);
	        }

	        System.out.print("\n== ǰ�����: \n");
	        tree.preOrder();

	        System.out.print("\n== �������: \n");
	        tree.inOrder();

	        System.out.print("\n== �������: \n");
	        tree.postOrder();
	        System.out.println();

	        System.out.println("== ��Сֵ: "+ tree.minimum());
	        System.out.println("== ���ֵ: "+ tree.maxinum());
	        System.out.println("== ������ϸ��Ϣ: ");
	        tree.print();

	        System.out.print("\n== ɾ�����ڵ�: "+ arr[3]);
	        tree.remove(arr[3]);

	        System.out.print("\n== �������: ");
	        tree.inOrder();
	        System.out.println();

	        // ���ٶ�����
	        tree.clear();
	}
}
