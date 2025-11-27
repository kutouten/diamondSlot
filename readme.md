## パッケージ分けについて
- commands : コマンド呼び出し系
- gui
    - handlers : 引数がイベントのみのものは大体ここ
    - managers : 主なGUI処理
    - menus : invのセットアップ用
- items
    - manager : pl独自のアイテムはここで定義
- listners : イベント取得のみ
- util : 全般使いそうな処理