package singleSrcMinDistance;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Dijkstra {
	static class Node {
		int index,distance;
		public Node(int index, int d) {
			this.index=index;
			//this.distance=d;
		}
	}
	
	public static void example() {
		//ͼ-�ڽӾ���
		int[][]map=new int[6][6];
		for(int i=0;i<6;i++) {
			for(int j=0;j<6;j++) {
				map[i][j]=Integer.MAX_VALUE;
			}
		}
		//Ĭ��0ΪԴ�㣬5Ϊ���
		map[0][1]=5;
		map[0][2]=0;
		map[1][3]=15;
		map[1][4]=20;
		map[2][3]=30;
		map[2][4]=35;
		map[3][5]=20;
		map[4][5]=10;
		
		//����
		dijkstra(map,0);
		
	}
	
	public static void dijkstra(int[][]map, int start) {
		//���
		int[]costs=new int[map.length];
		for(int i=0;i<costs.length;i++) {
			costs[i]=Integer.MAX_VALUE;//��ʼ��
		}
		costs[start]=0;
		int[] fathers=new int[map.length];//�����i��ǰ�����
		boolean[]flag=new boolean[map.length];//false-δ����
		
		Queue<Node> queue=new PriorityQueue<Node>(
				new Comparator<Node>() {
					@Override
					public int compare(Node o1, Node o2) {
						return o1.distance-o2.distance;
					}
				});
		queue.offer(new Node(start,0));
		
		while(!queue.isEmpty()) {
			Node cur=queue.poll();
			int index=cur.index;
			flag[index]=true;
			for(int i=0;i<map.length;i++) {
				if(!flag[i]&&map[index][i]<Integer.MAX_VALUE) {
					int new_cost=map[index][i]+cur.distance;
					if(new_cost<costs[i]) {
						costs[i]=new_cost;
						fathers[i]=index;
						queue.offer(new Node(i, new_cost));
					}
				}
			}
		}
		
		for(int t:costs) {
			System.out.println(t);
		}
		int father=5;
		System.out.println("from "+start+" to "+father+" minPath=");
		Stack<Integer> stack=new Stack<>();
		stack.push(father);
		while(fathers[father]!=start) {
			stack.push(fathers[father]);
			father=fathers[father];
		}
		stack.push(start);
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}
		System.out.println("mindistnace="+costs[5]);
	}
	
	public static void main(String[] args) {
		example();
	}
}
