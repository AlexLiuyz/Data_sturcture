#include <iostream>
#include <fstream>
#include <vector>
#include<cstring>
#include <queue>
#include <limits>
#include<algorithm>
#include<stdlib.h>
using namespace std;


// Define a struct to represent each node in the graph
struct Node {
    public:
        char place_of_origin_[80];//籍贯
        int force_;//武力
        int intelligence_;//智力
        char original_identity_[80];//原始身份 
        char name_[90];
        char team_[50];//小团队
        vector<pair<Node*,pair<char*,int> > > neighbors_;//store the node and weight in the graph
        Node(char name[]){
            strcpy(this->name_, name);
        }
        Node(){}
        Node(char name[],char place_of_origin[],char original_identity[]){
            strcpy(this->name_, name);
            strcpy(this->place_of_origin_,place_of_origin);
            strcpy(this->original_identity_,original_identity);
        }
        Node(char name[],char place_of_origin[],char original_identity[],int force,int intelligence):force_(force),intelligence_(intelligence){
            strcpy(this->name_, name);
            strcpy(this->place_of_origin_,place_of_origin);
            strcpy(this->original_identity_,original_identity);
        }
};

class Graph {
    public:
        Graph() {}
        vector<Node> dijkstra(char name[]);
        //check if the relationship is valid
        int check_validation_relation(char relation[]);
        //assign weight to different edges
        void relation_weight(pair<Node*,pair<char*,int> > *edge,Node *person);
        // to full fill the 7th requirement, assign weight to different edges
        void assign_weight_to(Node* person);
        // Insert a node into the graph
        void insert_node(char name[],char original_identity[],char place_of_origin[],char age[],char intelligence[],char force[]);
        //find the node belongs to which team
        void assign_to_team(char name[],char team_name[]);
        //print the node in a specified team
        void check_according_to_team_name(char team_name[]);
        //this function is for remove a node from the structure
        void rm_node(char name[]);
        //if the user want to edit the relationship between different person
        void change_relationship(char command[]);
        //print the relationship between person
        void find_relationship(char name1[],char name2[]);
        //build relationship between two different person
        void add_edge(char name1[],char name2[],char relationship[]);
        //to show the content of the whole network
        void print_graph();
        void print_node(char name[]);
        //search the node according to its name
        Node* search_by_name(char name[]);
        //write all the personal information to file
        void graph_to_information_file();
        //write the relationship to the file
        void graph_to_relationship_file();
        //reconstruct the graph information
        void reconstruct_information();
    private:
        vector<Node> graph;
        vector<Node> team_node;
        bool if_exist(char name[]){
            return search_by_name(name)!=NULL;
        }
};

        vector<Node> Graph::dijkstra(char name[]) {
            Node* start=search_by_name(name);
            vector<Node> path;
            if(start==NULL){printf("The name of person u want to get in touch is not exist.\n");return path;}
            int n = graph.size();
            vector<int> dist(n, numeric_limits<int>::max()); // initialize distances to infinity
            vector<Node*> prev(n, NULL); // initialize previous nodes to null
            dist[start - graph.data()] = 0; // distance from start node to itself is 0
            priority_queue<pair<int, Node*>, vector<pair<int, Node*> >, greater<pair<int, Node*> > > pq; // min-heap of pairs (distance, node)
            pq.push(make_pair(0, start));
            Node* boundary;
            while (!pq.empty()) {
                Node* u = pq.top().second; // extract node with smallest distance
                pq.pop();
                if (u->neighbors_.empty()) { // stop when a boundary node is reached
                    boundary=u;
                    break;
                }
                for(vector<pair<Node*,pair<char*,int> > >::iterator neighbor=u->neighbors_.begin();neighbor!=u->neighbors_.end();neighbor++){
                    Node* v = neighbor->first;
                    int weight = neighbor->second.second;
                    int alt = dist[u - graph.data()] + weight; // calculate tentative distance to neighbor
                    if (alt < dist[v - graph.data()]) { // if new distance is smaller than current distance
                        dist[v - graph.data()] = alt; // update distance to neighbor
                        prev[v - graph.data()] = u; // update previous node for neighbor
                        pq.push(make_pair(alt, v)); // add neighbor to priority queue
                    }
                }
            }
            for (Node* u = prev[n - 1]; u != NULL; u = prev[u - graph.data()]) {
                path.push_back(*u);
            }
            reverse(path.begin(), path.end()); // reverse path
            path.push_back(*boundary);
            return path;
        }
        //check if the relationship is valid
        int Graph::check_validation_relation(char relation[]){
            if(strcmp(relation,"血缘")==0||strcmp(relation,"夫妻")==0||strcmp(relation,"亲属")==0||strcmp(relation,"死忠")==0||strcmp(relation,"结拜")==0
            ||strcmp(relation,"恩遇")==0||strcmp(relation,"师徒")==0||strcmp(relation,"上下级")==0
            ||strcmp(relation,"投靠")==0||strcmp(relation,"招募")==0||strcmp(relation,"好友")==0||strcmp(relation,"")==0){
                    return 1;
            }
            return -1;
        }
//assign weight to different edges
        void Graph::relation_weight(pair<Node*,pair<char*,int> > *edge,Node *person){
            if(strcmp(edge->second.first,"血缘")==0||strcmp(edge->second.first,"夫妻")==0||strcmp(edge->second.first,"亲属")==0||strcmp(edge->second.first,"死忠")==0||strcmp(edge->second.first,"结拜")==0){
                edge->second.second=1;
            }
            else if(strcmp(edge->second.first,"恩遇")==0||strcmp(edge->second.first,"师徒")==0||strcmp(edge->second.first,"上下级")==0){
                edge->second.second=2;
            }
            else if(strcmp(edge->second.first,"投靠")==0||strcmp(edge->second.first,"招募")==0||strcmp(edge->second.first,"好友")==0||strcmp(edge->second.first,"")==0){
                edge->second.second=3;
            }
            if(strcmp(person->place_of_origin_,edge->first->place_of_origin_)==0){//相同籍贯使得关系更近 
                edge->second.second-=1;
            }
            if(strcmp(person->original_identity_,edge->first->original_identity_)==0){//相同社会地位使得关系更近 
                edge->second.second-=0.5;
            }
        }
// to full fill the 7th requirement, assign weight to different edges
        void Graph::assign_weight_to(Node* person){
            for(vector<Node>::iterator node=graph.begin();node!=graph.end();node++){
                for(vector<pair<Node*,pair<char*,int> > >::iterator neighbor=node->neighbors_.begin();neighbor!=node->neighbors_.end();neighbor++){
                    relation_weight(&(*neighbor),&(*person));
                }
            }
        }
void Graph::insert_node(char name[],char original_identity[],char place_of_origin[],char age[],char intelligence[],char force[]) {
            Node* node = new Node(name,place_of_origin,original_identity,atoi(force),atoi(intelligence));
            graph.push_back(*node);
        }
 //find the node belongs to which team
        void Graph::assign_to_team(char name[],char team_name[]){
            bool exist=false;
            Node* current_team_node=(Node*)malloc(sizeof(Node));
            if(!team_node.empty()){
                for(vector<Node>::iterator node=team_node.begin();node!=team_node.end();node++){
                    if(strcmp(node->name_,team_name)==0){
                        Node *current_team_node=&(*node);
                        Node *current_node=search_by_name(name);
                        strcpy(current_node->team_,team_name);
                        current_team_node->neighbors_.push_back(make_pair(current_node,make_pair(team_name,0)));
                        exist=true;
                        break;
                    }
                }
            }
            if(!exist){
                Node *new_team_node=new Node(team_name);
                Node *current_node = search_by_name(name);
                strcpy(current_node->team_,team_name);
                new_team_node->neighbors_.push_back(make_pair(current_node,make_pair(team_name,0)));
                team_node.push_back(*new_team_node);
                return;
            }
        }
//print the node in a specified team
        void Graph::check_according_to_team_name(char team_name[]){
            if(!team_node.empty()){
                for(vector<Node>::iterator node=team_node.begin();node!=team_node.end();node++){
                    if(strcmp(node->name_,team_name)==0){
                        for(vector<pair<Node*,pair<char*,int> > > ::iterator team_member=node->neighbors_.begin();team_member!=node->neighbors_.end();team_member++){
                            printf("%s\n",team_member->first->name_);
                        }
                        return;
                    }
                }
            }
        }
//this function is for remove a node from the structure
        void Graph::rm_node(char name[]){
            int index;
            if(name==NULL){return;}
            for(vector<Node>::iterator node=team_node.begin();node!=team_node.end();node++){
                for(vector<pair<Node*,pair<char*,int> > >::iterator team_member=node->neighbors_.begin();team_member!=node->neighbors_.end();team_member++){
                    if(strcmp(team_member->first->name_,name)==0){
                         node->neighbors_.erase(team_member);
                         break;
                    }
                }
            }
            int index1=0;
            for (vector<Node>::iterator it = graph.begin(); it != graph.end(); ++it) {
                if (strcmp(it->name_,name) == 0) {
                    graph.erase(it);
                    break;
                }
                for(vector<pair<Node*,pair<char*,int> > >::iterator member=it->neighbors_.begin();member!=it->neighbors_.end();member++){
                    if(strcmp(member->first->name_,name)==0){
                        it->neighbors_.erase(member);
                        break;
                    }
                }
            }
        }
//if the user want to edit the relationship between different person
        void Graph::change_relationship(char command[]){
            char *component;
            char s[2]="-";
            char *Name=(char*)malloc(sizeof(char)*30);
            component=strtok(command,s);
            if(strcmp(component,"rm")==0){
                while(component!=NULL){
                    component=strtok(NULL,s);
                    rm_node(component);
                }
            }
            else if(strcmp(component,"rcg")==0){
                char*name1=strtok(NULL,s);
                char*name2=strtok(NULL,s);
                char *new_relationship=strtok(NULL,s);
                Node *node1=search_by_name(name1);
                Node *node2=search_by_name(name2);
                if(node1==NULL||node2==NULL){printf("The person does not exist!!!");return;}
                for(vector<pair<Node*,pair<char*,int> > >::iterator neighbor=node1->neighbors_.begin();neighbor!=node1->neighbors_.end();neighbor++){
                    if(strcmp(neighbor->first->name_,name2)==0){
                        strcpy(neighbor->second.first,new_relationship);
                        break;
                    }
                }
            }
            else if(strcmp(component,"cg")==0){
                char *Name=strtok(NULL,s);
                Node* node=search_by_name(Name);
                for(vector<Node>::iterator node=team_node.begin();node!=team_node.end();node++){
                    for(vector<pair<Node*,pair<char*,int> > >::iterator team_member=node->neighbors_.begin();team_member!=node->neighbors_.end();team_member++){
                        if(strcmp(team_member->first->name_,Name)==0){
                            node->neighbors_.erase(team_member);
                            break;
                        }
                    }
                }
                char *team_name=strtok(NULL,s);
                assign_to_team(Name,team_name);
            }
            else if(strcmp(component,"edit")==0){
                printf("input the name of the person u want to edit(cannot change team here).\n");
                cin>>Name;
                Node *node=search_by_name(Name);
                while(true){
                    char *new_command=(char*)malloc(sizeof(char)*100);
                    char *condition=(char*)malloc(sizeof(char)*10);
                    char *temp;
                    printf("input command(e.g name-鏉庨€?placeoforigin-灞辫タ,age-80)for changing field, please use small case\n");
                    cin>>new_command;
                    component=strtok(new_command,s);
                    temp=component;
                    component=strtok(NULL,s);
                    if(strcmp(temp,"name")==0){strcpy(node->name_,component);}
                    //else if(strcmp(temp,"age")==0){node->age_=atoi(component);}
                    else if(strcmp(temp,"placeoforigin")==0){strcpy(node->place_of_origin_,component);}
                    else if(strcmp(temp,"intelligence")==0){node->intelligence_=atoi(component);}
                    else if(strcmp(temp,"force")==0){node->force_=atoi(component);}
                    else if(strcmp(temp,"original_identity")==0){strcmp(node->original_identity_,component);}
                    else{printf("Warning:wrong command\n");}
                    printf("want to continue changing?(input n for quit, any other input for continue)\n");
                    cin>>condition;
                    if(strcmp(condition,"n")==0){break;}
                }
            }
        }
//print the relationship between person
        void Graph::find_relationship(char name1[],char name2[]){
            if(if_exist(name1)&&if_exist(name2)){
                Node *node=search_by_name(name1);
                for (vector<pair<Node*,pair<char*,int> > >::iterator neighbor=node->neighbors_.begin();neighbor!=node->neighbors_.end();neighbor++) {
                    if(strcmp(neighbor->first->name_,name2)==0){
                        printf("%s\n",neighbor->second);
                        return;
                    }
                }
            }
            else{
                printf("Warning: The person u input maybe not exist.\n");
            }
            printf("普通\n");
        }
//build relationship between two different person
        void Graph::add_edge(char name1[],char name2[],char relationship[]) {
            Node *node1 = search_by_name(name1);
            Node *node2 = search_by_name(name2);
            if(node1==NULL || node2==NULL){
                return;
            }
            char *temp=(char*)malloc(sizeof(char)*50);
            strcpy(temp,relationship);
            node1->neighbors_.push_back(make_pair(node2,make_pair(temp,0)));
            // printf("%s\n",node1->neighbors_.begin()->second);
            node2->neighbors_.push_back(make_pair(node1,make_pair(temp,0)));
        }
//to show the content of the whole network
        void Graph::print_graph() {
            char* output=(char*)malloc(sizeof(char)*5000);
            if(graph.empty()){printf("The graph is empty\n");return;}
            for(vector<Node>::iterator node=graph.begin();node!=graph.end();node++){
                sprintf(output,node->name_);
                for (vector<pair<Node*,pair<char*,int> > >::iterator neighbor=node->neighbors_.begin();neighbor!=node->neighbors_.end();neighbor++) {
                    sprintf(output, "%s %s relationship: %s\n", output, neighbor->first->name_,neighbor->second.first);
                }
                printf("%s\n", output);
            }
        }
        void Graph::graph_to_information_file(){
            FILE *wf=fopen("Information.txt","w+");
            char *input=(char*)malloc(sizeof(char)*10000);
            for(vector<Node>::iterator node=graph.begin();node!=graph.end();node++){
                sprintf(input,"%s %s %d %d %s %s\n",node->name_, node->place_of_origin_, node->force_, node->intelligence_, node->original_identity_, node->team_);
                fputs(input,wf);
            }
        }
        void Graph::graph_to_relationship_file(){
            FILE *wf=fopen("Relationship.txt","w+");
            char *input=(char*)malloc(sizeof(char)*10000);
            for(vector<Node>::iterator node=graph.begin();node!=graph.end();node++){
                for (vector<pair<Node*,pair<char*,int> > >::iterator neighbor=node->neighbors_.begin();neighbor!=node->neighbors_.end();neighbor++) {
                    sprintf(input, "%s %s %s\n", node->name_,neighbor->first->name_,neighbor->second.first);
                    fputs(input,wf);
                }
            }
        }
        void Graph::reconstruct_information(){
            FILE *fp = fopen("Information.txt", "a+");
            char *token;
            char *line=(char*)malloc(sizeof(char)*10000);
            while (fscanf(fp, "%10000[^\n]\n", line) == 1) {
                Node node;
                token = strtok(line, " ");
                strcpy(node.name_,token);
                token = strtok(NULL," ");
                strcpy(node.place_of_origin_,token);
                token = strtok(NULL," ");
                node.force_=atoi(token);
                token = strtok(NULL," ");
                node.intelligence_=atoi(token);
                token = strtok(NULL," ");
                strcpy(node.original_identity_,token);
                token = strtok(NULL," ");
                strcpy(node.team_,token);
                graph.push_back(node);
                if(strcmp(node.team_,"n")!=0){assign_to_team(node.name_,node.team_);}
            }
            fclose(fp);
            char *name1=(char*)malloc(sizeof(char)*80);
            char *name2=(char*)malloc(sizeof(char)*80);
            char *relationship=(char*)malloc(sizeof(char)*80);
            fp=fopen("Relationship.txt","a+");
            while(fscanf(fp, "%10000[^\n]\n", line) == 1){
                token = strtok(line, " ");
                strcpy(name1,token);
                token = strtok(NULL, " ");
                strcpy(name2,token);
                token = strtok(NULL, " ");
                strcpy(relationship,token);
                add_edge(name1, name2, relationship);
            }
        }
void Graph::print_node(char name[]){
            Node* node=search_by_name(name);
            if(node==NULL){printf("The person u search does not exist\n");}
            printf("******Name: %s******\n",node->name_);
            //printf("Age: %d\n",node->age_);
            printf("Place of origin: %s\n",node->place_of_origin_);
            printf("Intelligence: %d\n",node->intelligence_);
            printf("Team: %s\n",node->team_);
            printf("Force: %d\n",node->force_);
            printf("Original identity: %s\n",node->original_identity_);
        }
void menu() {
    cout << "*********************************" << endl;
    cout << "***********main menu*************" << endl;
    cout << "*********************************" << endl;
    cout << "1. please input the information." << endl;
    cout << "2. choose to build relationship." << endl;
    cout << "3.find relationship between two persons." << endl;
    cout << "4.show the content of the graph." << endl;
    cout << "5.search according to group name" << endl;
    cout << "6.want to manage exist member?" << endl;
    cout << "7.The person you want to get in touch with,your identity." << endl;
    cout << "8.search person by his/her name." << endl;
}
void edit_menu(){
    cout << "input rm follow by name(e.g. rm-tracy-bruce)" <<endl;
    cout << "input ng followed by name and new group name in order to change the person to another group(e.g. cg-宋江-管理层)" << endl;
    cout << "input edit for edit personal information." << endl;
    cout<<"input rcg followed by name1 and name2 and new replationship(rcg-宋江-吴用-敌人)"<<endl;
}
//search the node according to its name
        Node* Graph::search_by_name(char name[]) {
            for(vector<Node>::iterator node=graph.begin();node!=graph.end();node++){
                if (strcmp(node->name_, name)==0) {
                    return &(*node);
                }
            }

            return NULL;
        }


