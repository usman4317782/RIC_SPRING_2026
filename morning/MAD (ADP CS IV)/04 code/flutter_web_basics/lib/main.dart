import 'package:flutter/material.dart';
import 'auth_screen.dart';

void main() {
  runApp(const PremiumAuthApp());
}

class PremiumAuthApp extends StatelessWidget {
  const PremiumAuthApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Premium Auth UI',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        brightness: Brightness.dark,
        primarySwatch: Colors.cyan,
        useMaterial3: true,
        fontFamily: 'Roboto', // Default font, but fits the modern look
      ),
      home: const AuthScreen(),
    );
  }
}
