import java.util.*;
import java.nio.file.*;

class Tagclass extends Stackoverflow{
    
    
    void print(String userid[],String tags[],String searchtag){
        HashMap<String, String> map = new HashMap<>(); 
        for(int i=0;i<50;i++){
            if(tags[i].indexOf(searchtag)!=-1){
                map.put(userid[i],searchtag);
            }
        }
        
        if(map.isEmpty()) { 
            System.out.println("map is empty"); 
        }
        else{ 
            System.out.println(map); 
        }
    }
}

class Stackoverflow{
    String question[]=new String[50];
    String queslink[]=new String[50];
    String tags[]=new String[50];
    int no_of_views[]=new int[50];
    int no_of_answers[]=new int[50];
    String userid[]=new String[50];
    String date[]=new String[50];
    String linkuserprofile[]=new String[50];
    String answersnippet[]=new String[50];
    String reputation_score[]=new String[50];
    int gold[]=new int[50];
    int silver[]=new int[50];
    int bronze[]=new int[50];
    int kq=0,kql=0,kt=0,kv=0,ka=0,ku=0,kd=0,klp=0,kas=0,krs=0,kg=0,ks=0,kb=0;
    
    void readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        String s[]=data.split("\n");
        // System.out.println("\n"+s.length);
        
        for(int i=0;i<s.length;i++){     
        
            int b=s[i].indexOf("class=\"question-hyperlink");
            if(b>=0 && b<s.length)
            {
                // question
                int d=s[i].indexOf("class=\"question-hyperlink\">");
                int e=s[i].indexOf("</a>");
                String str1=s[i].substring(d+27,e);
                question[kq]=str1;
                kq++;
                // System.out.println(str1);
            }
            if(b>=0 && b<s.length)
            {
                // question link
                int c=s[i].indexOf("href=\"/questions/" );
                int d=s[i].indexOf("class=\"question-hyperlink");
                String str1="https://stackoverflow.com"+s[i].substring(c+6,d-2);
                queslink[kql]=str1;
                kql++;
                // System.out.println(str1);
            }
            b=s[i].indexOf("class=\"views");
            if(b>=0 && b<s.length)
            {
                // no. of views
                int c=s[i].indexOf("title=\"");
                int d=s[i].indexOf(" views\">");
                String str1=s[i].substring(c+7,d).replace(",","");
                no_of_views[kv]=Integer.parseInt(str1);
                kv++;
                // System.out.println(str1);
            }
            b=s[i].indexOf("<div class=\"status answered-accepted\">");
            if(b>=0 && b<s.length)
            {
                // no. of answers
                int c=s[i+1].indexOf("<strong>");
                int d=s[i+1].indexOf("</strong>");
                String str1=s[i+1].substring(c+8,d).replace(",","");
                no_of_answers[ka]=Integer.parseInt(str1);
                ka++;
                // System.out.println(str1);
            }
            b=s[i].indexOf("<div class=\"user-details\">");
            if(b>=0 && b<s.length)
            {
                // user id
                int c=s[i+1].indexOf("\">");
                int d=s[i+1].indexOf("</a>");
                String str1=s[i+1].substring(c+1,d+1);
                String str2=str1.replace("\"","");
                String str3=str2.replace(">","");
                String str4=str3.replace("<","");
                userid[ku]=str4;
                ku++;
                // System.out.println(str4);
            }
            b=s[i].indexOf("class=\"relativetime\"");
            if(b>=0 && b<s.length)
            {
                // date and time
                int c=s[i].indexOf("<span title=\"");
                int d=s[i].indexOf("\" class=\"");
                String str1=s[i].substring(c+13,d);
                date[kd]=str1;
                kd++;
                // System.out.println(str1);
            }
            b=s[i].indexOf("<div class=\"user-details\">");
            if(b>=0 && b<s.length)
            {
                // link to user profile
                int c=s[i+1].indexOf("<a href=\"");
                int d=s[i+1].indexOf("\">");
                String str1=s[i+1].substring(c+1,d+1);
                String str2="https://stackoverflow.com"+str1.replace("a href=\"","");
                String str3=str2.replace("\"","");
                linkuserprofile[klp]=str3;
                klp++;
                // System.out.println(str3);
            }
            b=s[i].indexOf("<div class=\"excerpt\">");
            if(b>=0 && b<s.length)
            {
                // answers
                int j=0;
                String str="";
                while(true){
                    if(s[j+i].indexOf("</div>")!=-1){
                        break;
                    }
                    else{
                        if(s[i+j].length()>0)
                            str+=s[j+i].substring(0,s[j+i].length()-1).replace("<div class=\"excerpt\">","").trim();
                        j++;
                    }
                }
                answersnippet[kas]=str;
                kas++;
                // System.out.println(str);
            }
            b=s[i].indexOf("<div class=\"tags ");
            if(b>=0 && b<s.length)
            {
                // tags
                int c=s[i].indexOf("<div class=\"");
                int d=s[i].indexOf("\">");
                String str1=s[i].substring(c+17,d);
                String str2=str1.replace("t-","");
                tags[kt]=str2;
                kt++;
                // temp.tags=str2;
                // System.out.println(str2);
            }
            b=s[i].indexOf("<div class=\"-flair\">");
            if(b>=0 && b<s.length)
            {
                // additional info
                int d,e;
                String str="0";
                d=s[i+1].indexOf("<span class=\"reputation-score\" title=\"reputation score \" dir=\"ltr\">");
                e=s[i+1].indexOf("</span>");
                if(d!=-1 && e!=-1)
                    str=s[i+1].substring(d+67,e);
                reputation_score[krs]=str;
                krs++;
            }
            // badges
            int b1=s[i].indexOf("class=\"badge1\">");
            int b2=s[i].indexOf("class=\"badge2\">");
            int b3=s[i].indexOf("class=\"badge3\">");
            if(b1>=0 && b1<s.length && kg<50){
                String str1=s[i].substring(b1+47,b1+48);
                gold[kg]=Integer.parseInt(str1);
                kg++;
            }
            if(b2>=0 || b3>=0 && kg<50){
                kg++;
            }
            if(b2>=0 && b2<s.length && ks<50)
            {
                String str1=s[i].substring(b2+47,b2+49);
                if((str1.charAt(str1.length()-1))=='<')
                {
                    str1=str1.substring(0,str1.length()-1);
                }
                silver[ks]=Integer.parseInt(str1);
                ks++;
            }
            if(b1>=0 || b3>=0 && ks<50){
                ks++;
            }
            if(b3>=0 && b3<s.length && kb<50){
                String str1=s[i].substring(b3+47,b3+49);
                if((str1.charAt(str1.length()-1))=='<'){
                    str1=str1.substring(0,str1.length()-1);
                }
                bronze[kb]=Integer.parseInt(str1);
                kb++;
            }
            if(b2>=0 || b1>=0 && kb<50)
            {
                kb++;
            }
        }
        Tagclass tc=new Tagclass();
        String searchtag="python";    //user input
        tc.print(userid,tags,searchtag);


        for(int m=0;m<50;m++){
            System.out.println("\n"+question[m]+"\n"+queslink[m]+"\ntags: "+tags[m]
            +"\nview : "+no_of_views[m]+" answers : "+no_of_answers[m]
            +"\nuserid : "+userid[m]+" reputationscore : "+reputation_score[m]+" "+gold[m]+" "+silver[m]+" "+bronze[m]
            +" date : "+date[m]+"\nprofile : "
            +linkuserprofile[m]+"\nanswer : "+answersnippet[m]);
        }
    }
}

class UserProfileSO extends Stackoverflow{
    void badgesearch(int badge1){
        for(int i = 0; i < 50; i++){
            if(gold[i] == badge1)
                System.out.println(userid[i] +" "+badge1+" gold badges");
            if(bronze[i]==badge1)
                System.out.println(userid[i]+" "+badge1+" bronze badges");
            if(silver[i]==badge1)
                System.out.println(userid[i]+" "+badge1+" silver badges");
        }
    }
    void badgesearch(int badge1,int badge2) {
        for(int i=0;i<50;i++){
            if(bronze[i]==badge2 || bronze[i]==badge1){
                if(gold[i] == badge1)
                    System.out.println(userid[i] +" "+badge1+" gold badges");        
                if(silver[i] == badge2)
                    System.out.println(userid[i] +" "+badge2+" silver badges");
            }
        }
    }
    void badgesearch(int badge1,int badge2,int badge3){
        for(int i=0;i<50;i++){
            if(gold[i]==badge1 || gold[i]==badge2 || gold[i]==badge3){    
                if(gold[i]==badge1)
                    System.out.println(userid[i]+" "+badge1+" gold badges");
                if(silver[i]==badge2)
                    System.out.println(userid[i]+" "+badge2+" silver badges");
                if(bronze[i]==badge3)
                    System.out.println(userid[i]+" "+badge3+" bronze badges");
            }
        }
    }
}


public class Assign08135{
    
    public static void main(String[] args) throws Exception {
        Stackoverflow sof[]=new Stackoverflow[100];
        for(int i=0;i<100;i++){
            sof[i]=new Stackoverflow();
        }  
        String file[]=new String[100];
        int j=25145;
        for(int i=0;i<100;i++){
            file[i]="./sof-dataset/sof-dataset/"+Integer.toString(j)+".html";
            j++;
        }

        for(int i=0;i<100;i++)
        {
            // System.out.println(file[i]);
            sof[i].readFileAsString(file[i]);
        }
        // sof[0].readFileAsString(file[0]);
        
    }
}