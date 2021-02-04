package singleSrcMinDistance;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import singleSrcMinDistance.BellmanFord.Node;

//队列优化版的BellmanFord算法-SPFA
public class SPFA {
	static boolean spfa(Node[] heads, int start) {
		System.out.println("sfpa:");
		int n = heads.length;
		int[] dist = new int[n];
		int[] father = new int[n];
		int INF = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++)
			dist[i] = INF;
		dist[start] = 0;
		father[start] = 0;
		Queue<Integer> queue=new LinkedList<>();
		int[]cnt=new int[n];
		queue.offer(start);
//		boolean[]flag=new boolean[heads.length];
		boolean negative=false;
		while (!queue.isEmpty()) {
			int j=queue.poll();
//			flag[j]=true;
			for (Node cur = heads[j]; cur != null; cur = cur.next) {
				int weight = cur.weight;
				int index = cur.index;
				if (weight != INF && dist[index] > dist[j] + weight) {
					dist[index] = dist[j] + weight;
					father[index] = j;
					if(cnt[index]>=n) {
						negative=true;
						break;
						
					}
					cnt[index]=cnt[index]+1;
					queue.offer(cur.index);
				}
				if(negative) break;
			}
		}
		System.out.println(Arrays.toString(cnt));
		
		for (int i = 0; i < dist.length; i++) {
			System.out.print(dist[i] + " ");
		}
		System.out.println();
		System.out.println(Arrays.toString(father));
		//求解后验证是否会收敛，若存在和为负数的环，则会一直减小，说明无解
		
		
		/*
		 * while(!q.empty()){
	        int x=q.front();q.pop();
	        v[x]=0;
	        for(int i=head[x];i;i=nxt[i]){
	            int y=ver[i],z=edge[i];
	            if(dis[y]>dis[x]+z){
	                dis[y]=dis[x]+z;
	                if(!v[y]){
	                    cnt[y]=cnt[x]+1;
	                    if(cnt[y]>=n) return 0;
	                    v[y]=1;q.push(y);
	                } 
	            }
	        }
	    }*/
		

		for (int i = 0; i < dist.length; i++) {
			System.out.print(dist[i] + " ");
		}
		return !negative;
	}
	
	public static void example() {
		Node[] nodes = new Node[6];
		nodes[0] = new Node(0, 0);
		nodes[0].insert(new Node(1, 2)).insert(new Node(2, 3)).insert(new Node(3, 6));

		nodes[1] = new Node(1, 0);
		nodes[1].insert(new Node(0, 2)).insert(new Node(4, 4)).insert(new Node(5, 6));

		nodes[2] = new Node(2, 0);
		nodes[2].insert(new Node(0, 3)).insert(new Node(3, 2));

		nodes[3] = new Node(3, 0);
		nodes[3].insert(new Node(0, 6)).insert(new Node(2, 2)).insert(new Node(4, 1));

		nodes[4] = new Node(4, 0);
		nodes[4].insert(new Node(3, 1)).insert(new Node(1, 4));

		nodes[5] = new Node(5, 0);
		nodes[5].insert(new Node(1, 6)).insert(new Node(3, 3));

		System.out.println(spfa(nodes, 0));
	}

	public static void example2() {
		/**
		 * //默认0为源点，5为汇点 map[0][1]=5; map[0][2]=0; map[1][3]=15; map[1][4]=20;
		 * map[2][3]=30; map[2][4]=35; map[3][5]=20; map[4][5]=10;
		 */
		Node[] nodes = new Node[6];
		nodes[0] = new Node(0, 0);
		nodes[0].insert(new Node(1, 5)).insert(new Node(2, 0));
		nodes[1] = new Node(1, 0);
		nodes[1].insert(new Node(3, 15)).insert(4, 20);
		nodes[2] = new Node(2, 0);
		nodes[2].insert(3, 30).insert(4, 35);
		nodes[3] = new Node(3, 0);
		nodes[3].insert(5, 20);
		nodes[4] = new Node(4, 0);
		nodes[4].insert(5, 10);

		System.out.println(spfa(nodes, 0));
	}
	
	public static void example3() {
		Node[] nodes = new Node[9];
		nodes[0] = new Node(0, 0);
		nodes[0].insert(new Node(1, 3)).insert(new Node(2, 2));
		nodes[1] = new Node(1, 0);
		nodes[1].insert(new Node(3,2)).insert(7,4);
		nodes[2] = new Node(2, 0);
		nodes[2].insert(4,1);
		nodes[3] = new Node(3, 0);
		nodes[3].insert(4,3);
		nodes[4] = new Node(4, 0);
		nodes[4].insert(5,1).insert(6,4).insert(3,3);
		nodes[5] = new Node(5, 0);
		nodes[5].insert(2,-3);
		nodes[6]=new Node(6,0);
		nodes[6].insert(8,5);
		nodes[7]=new Node(7,0);
		nodes[7].insert(8,1);
		
		System.out.println(spfa(nodes, 0));
	}

	public static void main(String[] args) {
		example();
		example2();
		example3();
	}
}