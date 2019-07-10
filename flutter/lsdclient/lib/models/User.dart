class User extends Object {
  String token; //字母
  String userName;
  String realName;
  String mobile;
  String orgName;


  User({this.token, this.userName,this.realName, this.mobile, this.orgName});

  User.empty();

  Map<String, dynamic> toMap() {
    var map = new Map<String, dynamic>();
    map['token'] = token;
    map['userName'] = userName;
    map['realName'] = realName;
    map['mobile'] = mobile;
    map['orgName'] = orgName;
    return map;
  }

  Map<String, dynamic> toJson(User user) {
    var map = new Map<String, dynamic>();
    map['token'] = user.token;
    map['userName'] = user.userName;
    map['realName'] = user.realName;
    map['mobile'] = user.mobile;
    map['orgName'] = user.orgName;
    return map;
  }

  static User fromJson(Map<String, dynamic> json) {
    User user = new User();
    user.token = json["token"];
    user.userName = json["userName"];
    user.realName = json["realName"];
    user.mobile = json["mobile"];
    user.orgName = json["orgName"];
    return user;
  }

  @override
  String toString() {
    // TODO: implement toString
    return """
    token : $token,
    userName : $userName,
    realName : $realName,
    mobile : $mobile,
    orgName : $orgName,
    """;
  }

}