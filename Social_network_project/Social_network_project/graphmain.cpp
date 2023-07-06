#include <iostream>
#include <fstream>
#include <vector>
#include<cstring>
#include <queue>
#include <limits>
#include<algorithm>
#include<stdlib.h>
#include"graph.cpp"
using namespace std;
int main() {
    // Create a Graph object and insert some nodes
    Graph graph;
    char *name=(char*)malloc(sizeof(char)*80);
    char *team_name=(char*)malloc(sizeof(char)*30);
    char *name1=(char*)malloc(sizeof(char)*80);
    char *name2=(char*)malloc(sizeof(char)*80);
    char *command=(char*)malloc(sizeof(char)*100);
    char *relationship=(char*)malloc(sizeof(char)*30);
    char *place_of_origin=(char*)malloc(sizeof(char)*80);
    char *original_identity=(char*)malloc(sizeof(char)*80);
    char *force=(char*)malloc(sizeof(char)*10);
    char *intelligence=(char*)malloc(sizeof(char)*80);
    char *age=(char*)malloc(sizeof(char)*80);
    vector<Node> path;
    Node* person;
    bool flag=true;
    while (true){
        if(flag){
            //read from the file
            graph.reconstruct_information();
            flag=false;
        }
        menu();
        int mode;
        cin >> mode;
        switch (mode) {
        case 1:;
            cout<<"input the information.does it belong to a team?(if not input n)\n";
            cin >> name>>place_of_origin>>intelligence>>force>>original_identity;
            graph.insert_node(name,original_identity,place_of_origin,age,intelligence,force);
            cin>>team_name;
            if(strcmp(team_name,"n")!=0){graph.assign_to_team(name,team_name);}
            break;
        case 2:
            cin >> relationship;
            cin >> name1;
            cin >> name2;
            if(graph.check_validation_relation(relationship)>0){
                graph.add_edge(name1, name2, relationship);
            }
            else{
                printf("Warning: The relationship is not exist.\n");
            }
            break;
        case 3:
            cin >> name1;
            cin >> name2;
            graph.find_relationship(name1,name2);
            break;
        case 4:
            graph.print_graph();
            break;
        case 5:
            cin>>team_name;
            graph.check_according_to_team_name(team_name);
            break;
        case 6:
            edit_menu();
            cin>>command;
            graph.change_relationship(command);
            break;
        case 7:
            printf("Please input his/her name:\n");
            cin>>name1;
            printf("Please input his/her place of origin:\n");
            cin>>place_of_origin;
            printf("Please input his/her original identity:\n");
            cin>>original_identity;
            person=new Node(name1,place_of_origin,original_identity);
            printf("Plase input the name of the person he/her want to find:\n");
            cin>>name2;
            graph.assign_weight_to(person);
            path=graph.dijkstra(name2);
            printf("The path is:");
            for(vector<Node>::iterator node=path.begin();node!=path.end();node++){
                printf("%s ->",node->name_);
            }
            printf("....\n");
            break;
        case 8:
            printf("please input the name of person that u want to search:\n");
            cin>>name;
            graph.print_node(name);
            break;
        default:
            char *choice=(char*)malloc(sizeof(char)*8);
            cout << "Wrong input please choose to exit or not(y for yes):\n";
            cin >> choice;
            if (strcmp(choice, "y") == 0) {
                graph.graph_to_information_file();
                graph.graph_to_relationship_file();
                return 0;
            }
        }
    }
    return 0;
}
