#include "AppDelegate.h"
#include "GeneratedPluginRegistrant.h"
@interface AppDelegate ()

@end
@implementation AppDelegate {
    BOOL _isActive;
}


- (BOOL)application:(UIApplication *)application
    didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
  [GeneratedPluginRegistrant registerWithRegistry:self];
    _isActive = NO;
  // Override point for customization after application launch.
    FlutterViewController* controller = (FlutterViewController*)self.window.rootViewController;
    FlutterMethodChannel *myChannel = [FlutterMethodChannel
                      methodChannelWithName:@"my_platform_Active"
                      binaryMessenger:controller];
    [myChannel setMethodCallHandler:^(FlutterMethodCall* call, FlutterResult result) {
        //TODO 获取版本信息 APP名、包名、版本号、build号
        if ([@"getActiveInfo" isEqualToString:call.method]) {
            BOOL myActive = self->_isActive;
            self->_isActive = NO;
            result(@{@"isActive": [NSNumber numberWithBool:myActive]});
        } else if ([@"setActiveInfo" isEqualToString:call.method]) {
            self->_isActive = NO;
            result(nil);
        } else {
            result(FlutterMethodNotImplemented);
        }
    }];
  return [super application:application didFinishLaunchingWithOptions:launchOptions];
}

- (void)applicationWillResignActive:(UIApplication *)application{
    [super applicationWillResignActive:application];
     //已经进入后台
    _isActive = YES;
}

- (void)applicationWillEnterForeground:(UIApplication *)application{
    [super applicationWillEnterForeground:application];
    //将要进入前台
    _isActive = YES;
}





@end
