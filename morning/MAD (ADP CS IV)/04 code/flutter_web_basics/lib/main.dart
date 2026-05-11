import 'package:flutter/material.dart';

void main() {
  runApp(const FlutterBasicsApp());
}

/// 1. WIDGETS: Everything in Flutter is a Widget.
/// The [FlutterBasicsApp] is the root widget of the application.
class FlutterBasicsApp extends StatelessWidget {
  const FlutterBasicsApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Web Basics',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        useMaterial3: true,
      ),
      home: const BasicConceptsPage(),
      debugShowCheckedModeBanner: false,
    );
  }
}

/// 2. STATE MANAGEMENT: [StatefulWidget] allows the UI to change dynamically.
class BasicConceptsPage extends StatefulWidget {
  const BasicConceptsPage({super.key});

  @override
  State<BasicConceptsPage> createState() => _BasicConceptsPageState();
}

class _BasicConceptsPageState extends State<BasicConceptsPage> {
  // This variable represents the 'State' of the application.
  int _counter = 0;
  bool _isPremiumTheme = false;

  void _incrementCounter() {
    // setState() tells the Flutter framework that something has changed,
    // which causes it to rerun the build method below.
    setState(() {
      _counter++;
    });
  }

  void _toggleTheme() {
    setState(() {
      _isPremiumTheme = !_isPremiumTheme;
    });
  }

  @override
  Widget build(BuildContext context) {
    // 3. LAYOUTS: Scaffold, Center, Column, and Padding are layout widgets.
    return Scaffold(
      appBar: AppBar(
        title: const Text('Widgets, Layouts & State'),
        backgroundColor: _isPremiumTheme ? Colors.deepPurple : Colors.blue,
        foregroundColor: Colors.white,
      ),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(20.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              // WIDGET: A Card to wrap content beautifully.
              Card(
                elevation: 4,
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(15)),
                color: _isPremiumTheme ? Colors.deepPurple.shade50 : Colors.blue.shade50,
                child: Padding(
                  padding: const EdgeInsets.all(30.0),
                  child: Column(
                    children: [
                      const Icon(Icons.flash_on, size: 50, color: Colors.orange),
                      const SizedBox(height: 10),
                      const Text(
                        'State Management Example',
                        style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                      ),
                      const SizedBox(height: 10),
                      Text(
                        'Counter Value: $_counter',
                        style: const TextStyle(fontSize: 40, color: Colors.blue),
                      ),
                    ],
                  ),
                ),
              ),
              const SizedBox(height: 30),
              
              // LAYOUT: Row is used to place widgets horizontally.
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  // WIDGET: ElevatedButton is an interactive widget.
                  ElevatedButton.icon(
                    onPressed: _incrementCounter,
                    icon: const Icon(Icons.add),
                    label: const Text('Increment'),
                    style: ElevatedButton.styleFrom(
                      padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 15),
                    ),
                  ),
                  const SizedBox(width: 20),
                  ElevatedButton.icon(
                    onPressed: _toggleTheme,
                    icon: const Icon(Icons.color_lens),
                    label: const Text('Toggle Theme'),
                    style: ElevatedButton.styleFrom(
                      backgroundColor: Colors.black87,
                      foregroundColor: Colors.white,
                      padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 15),
                    ),
                  ),
                ],
              ),
              
              const SizedBox(height: 40),
              const Text(
                'Concepts demonstrated:',
                style: TextStyle(fontStyle: FontStyle.italic),
              ),
              const Text('• Widgets (Buttons, Text, Icons, Cards)'),
              const Text('• Layouts (Column, Row, Center, Padding)'),
              const Text('• State Management (StatefulWidget & setState)'),
            ],
          ),
        ),
      ),
    );
  }
}
