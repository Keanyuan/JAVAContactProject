import 'package:flutter/material.dart';
import 'package:flutter_chips_input/flutter_chips_input.dart';


class InputChipPage extends StatefulWidget {
  InputChipPage({Key key}) : super(key: key);

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<InputChipPage> {
  @override
  Widget build(BuildContext context) {
    const mockResults = <AppProfile>[
      AppProfile('John Doe', 'jdoe@flutter.io',
          'https://d2gg9evh47fn9z.cloudfront.net/800px_COLOURBOX4057996.jpg'),
      AppProfile('Paul', 'paul@google.com',
          'https://mbtskoudsalg.com/images/person-stock-image-png.png'),
      AppProfile('Fred', 'fred@google.com',
          'https://media.istockphoto.com/photos/feeling-great-about-my-corporate-choices-picture-id507296326'),
      AppProfile('Brian', 'brian@flutter.io',
          'https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png'),
      AppProfile('John', 'john@flutter.io',
          'https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png'),
      AppProfile('Thomas', 'thomas@flutter.io',
          'https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png'),
      AppProfile('Nelly', 'nelly@flutter.io',
          'https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png'),
      AppProfile('Marie', 'marie@flutter.io',
          'https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png'),
      AppProfile('Charlie', 'charlie@flutter.io',
          'https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png'),
      AppProfile('Diana', 'diana@flutter.io',
          'https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png'),
      AppProfile('Ernie', 'ernie@flutter.io',
          'https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png'),
      AppProfile('Gina', 'fred@flutter.io',
          'https://upload.wikimedia.org/wikipedia/commons/7/7c/Profile_avatar_placeholder_large.png'),
    ];

    return Scaffold(
      appBar: AppBar(
        title: Text('Flutter Chips Input Example'),
      ),
      body: Padding(
        padding: EdgeInsets.all(20),
        child: Column(
          children: <Widget>[
            ChipsInput(
              initialValue: [
                AppProfile('John Doe', 'jdoe@flutter.io',
                    'https://d2gg9evh47fn9z.cloudfront.net/800px_COLOURBOX4057996.jpg'),
              ],
              enabled: true,
              maxChips: 10,
              decoration: InputDecoration(
                // prefixIcon: Icon(Icons.search),
                // hintText: formControl.hint,
                labelText: "Select People",
                // enabled: false,
                // errorText: field.errorText,
              ),
              findSuggestions: (String query) {
                if (query.length != 0) {
                  var lowercaseQuery = query.toLowerCase();
                  return mockResults.where((profile) {
                    return profile.name
                        .toLowerCase()
                        .contains(query.toLowerCase()) ||
                        profile.email
                            .toLowerCase()
                            .contains(query.toLowerCase());
                  }).toList(growable: false)
                    ..sort((a, b) => a.name
                        .toLowerCase()
                        .indexOf(lowercaseQuery)
                        .compareTo(
                        b.name.toLowerCase().indexOf(lowercaseQuery)));
                } else {
                  return const <AppProfile>[];
                }
              },
              onChanged: (data) {
                print(data);
              },
              chipBuilder: (context, state, profile) {
                return InputChip(
                  key: ObjectKey(profile),
                  label: Text(profile.name),
                  avatar: CircleAvatar(
                    backgroundImage: NetworkImage(profile.imageUrl),
                  ),
                  onDeleted: () => state.deleteChip(profile),
                  materialTapTargetSize: MaterialTapTargetSize.shrinkWrap,
                );
              },
              suggestionBuilder: (context, state, profile) {
                return ListTile(
                  key: ObjectKey(profile),
                  leading: CircleAvatar(
                    backgroundImage: NetworkImage(profile.imageUrl),
                  ),
                  title: Text(profile.name),
                  subtitle: Text(profile.email),
                  onTap: () => state.selectSuggestion(profile),
                );
              },
            ),
          ],
        ),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}

class AppProfile {
  final String name;
  final String email;
  final String imageUrl;

  const AppProfile(this.name, this.email, this.imageUrl);

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
          other is AppProfile &&
              runtimeType == other.runtimeType &&
              name == other.name;

  @override
  int get hashCode => name.hashCode;

  @override
  String toString() {
    return name;
  }
}