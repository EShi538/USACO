#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int t;
    cin >> t;
    for(int i = 0; i < t; i++){
        int n;
        cin >> n;
        string a, b;
        cin >> a >> b;
        vector<int> vect;
        vector<int> vect1;
        for(int i = 0; i < n; i++){
            if(i == n - 1){
                if(a.at(i) == '1'){
                    vect.push_back(i + 1);
                }
            }
            else{
                if(a.at(i) != a.at(i + 1)){
                    vect.push_back(i + 1);
                }
            }
        }
        for(int i = 0; i < n; i++){
            if(i == n - 1){
                if(b.at(i) == '1'){
                    vect1.push_back(i + 1);
                }
            }
            else{
                if(b.at(i) != b.at(i + 1)){
                    vect1.push_back(i + 1);
                }
            }
        }
        reverse(vect1.begin(), vect1.end());
        cout << vect1.size() + vect.size() << " ";
        for(int i: vect){
            cout << i << " ";
        }
        for(int i: vect1){
            cout << i << " ";
        }
        cout << "\n";
    }
}
