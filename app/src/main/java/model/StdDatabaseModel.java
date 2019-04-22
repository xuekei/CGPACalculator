package model;


public class StdDatabaseModel
{
  String StrSubCode;
  String StrSubName;
  int SubCredit;
  String StrGrade;


  public StdDatabaseModel(String StrSubCode,String StrSubName,int SubCredit, String StrGrade)
  {
      this.StrSubCode=StrSubCode;
      this.StrSubName=StrSubName;
      this.SubCredit=SubCredit;
      this.StrGrade=StrGrade;
  }
  public  StdDatabaseModel()
  {

  }

    public String getStrSubCode() {
        return StrSubCode;
    }

    public void setStrSubCode(String strSubCode) {
        StrSubCode = strSubCode;
    }

    public String getStrSubName() {
        return StrSubName;
    }

    public void setStrSubName(String strSubName) {
        StrSubName = strSubName;
    }

    public int getSubCredit() {
        return SubCredit;
    }

    public void setSubCredit(int subCredit) {
        SubCredit = subCredit;
    }

    public String getStrGrade() {
        return StrGrade;
    }

    public void setStrGrade(String strGrade) {
        StrGrade = strGrade;
    }
}
