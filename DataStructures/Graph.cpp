#include <iostream>
#include <list>
#include <vector>

using namespace std;

class Node
{
	public:
		int data;
		list<Node*> neighbours;
		Node()
		{
			data=0;
		}
		Node(int elm)
		{
			data=elm;
			neighbours=*(new list<Node*>);
		}
};

class Graph
{
	public:
		Node* nodes;
		int no_of_nodes;
		Graph(int no_of_nodes)
		{
			this->no_of_nodes=no_of_nodes;
			nodes=new Node[no_of_nodes];
			for(int i=0;i<no_of_nodes;i++)
				nodes[i]=*(new Node(i));
			createGraph();
		}
		void createGraph()
		{
			for(int i=0;i<no_of_nodes;i++)
			{
				cout<<"Enter the neighbours of node["<<i<<"](-1 to quit): "<<endl;
				int neighbour;
				cout<<"Enter neighbour: ";
				cin>>neighbour;
				while(neighbour!=-1)
				{
					if(neighbour>=no_of_nodes)
					{
						cout<<"Sorry wrong neighbour try again"<<endl;
						cout<<"Enter neighbour: ";
						cin>>neighbour;
						continue;
					}
					nodes[i].neighbours.push_back(&nodes[neighbour]);
					cout<<"Enter neighbour: ";
					cin>>neighbour;
				}
			}
		}
};

ostream& operator <<(ostream& out,Graph g)
{
	for(int i=0;i<g.no_of_nodes;i++)
	{
		out<<"Neighbours of node["<<i<<"]"<<endl;
		for(auto it=g.nodes[i].neighbours.begin();it!=g.nodes[i].neighbours.end();it++)
			out<<(*it)->data<<" ";
		out<<endl;
	}
	return out;

}
int main()
{
	Graph g(4);
	cout<<g;

}
