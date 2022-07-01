# 概要

大人気タイトル「大乱闘スマッシュブラザーズ」をより e-Sports 向けに磨き上げた対戦アクションゲーム、<br> "Rivals of Aether"「ライバルズオブエーテル」。
"Rivals Mate" は、ライバルズオブエーテルのプレイヤー同士の対戦・交流を目的としたWebアプリです。  

※当作品は個人の Rivals of Aether ファンが作成した非公式のWebアプリです。Fornace Daniel 様、他関連企業様とは一切関係ありません。

<br>

# 制作背景

ライバルズオブエーテル(以下RoAと表記)はとても洗練されたゲームです。子供の頃、友達と「スマブラ」で遊んだときに感じたあのワクワク・高揚感を、大人になった今でもまた味わうことができます。
しかし、国内でのRoAプレイヤーはまだまだ少ないのが現状です。この発展途上のコミュニティを少しでも盛り上げたいという想いから Rivals Mate は作られました。

Rivals Mate はコミュニティに３つの利益をもたらします。

  - **対戦の記録**<br>
RoAでどのくらい対戦が行われているか、つまりどのくらいコミュニティが活発かを一目で見ることがでます。新規参入者にとって、場数を踏んでいくための対戦交流が活発であることはモチベーションに大きく影響します。

  - **隠れたプレイヤーの発見**<br>
RoAコミュニティでは discord （通話チャットアプリ）を使って交流しています。しかし、文字や言葉だけの交流では「積極的に発言をするプレイヤー」は前面に立てるものの、「発言の少ないプレイヤー」は陰に隠れてしまいがちです。そこで、 Rivals Mate で勝率を上げることによって、誰でも平等に実力で存在を示すことができます。

  - **格付けによるモチベーションの奮起**<br>
現状、RoAコミュニティでは実力を示す明確な指標がありません。勝敗や勝率が明示される Rivals Mate を導入することによってプレイヤーたちの競争心を搔き立て、さらに上を目指すためのモチベーションを沸き起こします。

# 機能
・ログイン<br>
・ユーザー登録/削除<br>
・プロフィール閲覧/編集<br>
・対戦マッチング<br>
・試合一覧表示<br>
<br>
≪管理者機能≫<br>
・アカウント管理<br>
・アカウント一覧表示<br>
・アカウントの削除<br>
・試合結果管理<br>
・試合一覧表示<br>
・勝敗変更<br>
・試合の削除<br>

# 使用技術
Java<br>
HTML<br>
CSS<br>
JavaScript<br>
<br>

# 工夫した点

## 少ない知識・技術力を最大限に活かす

今やほとんどのWebアプリで使用されている JavaScript 。けれどもお恥ずかしながら、現状私にはその知識がありません。一旦勉強時間を確保し、腰を据えて JavaScript を学んでから制作に挑もうかと検討しました。しかし、そうすると目標である「ポートフォリオの完成、就職活動」が遠のいてしまいます。JavaScript を導入しないとなると、派手な動きや装飾のない落ち着いたWebアプリになってしまいますが、それでもまずは完成させることに全力を尽くそうと決めました。

※「Webブラウザの戻る/進むボタンによる操作を禁止する」という機能は HTML/CSS で実装できなかったため、 JavaScript を使用しています。

私が今持ち合わせているだけの技術力を駆使し、いかに理想に近い実装方法に辿り着けるか。今回の制作において最も念頭に置いたことであり、身についたことでもあります。

<br>
以下は Rivals Mate で見られる「ちょっとした工夫」です。

### 嵐の演出
JavaScript を使わないからといって、Webページの雰囲気作り・演出が全くできないわけではありません。メインメニューでは、沈む夕日に向かって吠えるファイターたち。しかし、一度闘いに身を投じると空模様が一変。そして嵐と共に対戦相手が襲来します。技術としては単純ですが、個人的にとても気に入っている演出です。
<br>
### キャラクター選択
プロフィール編集機能において、ゲーム内のキャラクター選択画面を再現しました。各キャラクター毎に画像が分割されていて、その一枚一枚がラジオボタンになっています。
<br>
### マッチング機能
「複数のユーザーが同時にWebアプリを操作し、かつ双方にリアルタイムで正しい結果を返さなければならない」。プログラミング経験の浅い私にとっては、どんなに複雑で高度な技術なのだろうと圧倒されていました。しかし蓋を開けて取り組んでみれば、単純な排他処理と既存の知識を組み合わせることにより実装することができました。

### エラーメッセージ表示
JavaScript であれば、ユーザーの文字入力に合わせてダイアログを表示できます。Rivals Mate ではそれに近い機能をリクエストスコープによって実現しています。

<br>
## RoAプレイヤーが親しみやすいデザイン
Webアプリにおいてまず大切なことは「ユーザーの手に取って使ってもらうこと」だと私は考えています。Webアプリがどんなに素晴らしい機能を提供していようとも、まずは使ってもらわなければその価値が伝わりません。
それを果たすために、Rivals Mate はゲーム内と同じようなデザイン・雰囲気に仕上げました。RoAプレイヤーにとって馴染みがあり、手の付け易いものとなっています。
そしてこのRoAの世界観に溶け込んだデザインによって、ただの「外部のファンサイト」では終わらず、あくまでゲームの延長上にあるような対戦ツールでありたい。そんな想いも込められています。

# 実装したい機能
・**プレイヤーランキング**<br>
<br>
・**プレイヤー地域表示**（任意選択）<br>

日本地図を表示して、その中に全プレイヤーの使用キャラクターのアイコンを分布します。<br>
「この地域はあのキャラクターが多いな」「あのトッププレイヤーがうちの近所に！？」といったような、オンライン上だけではなくオフラインでの繋がりも提供します。
<br>
・**キャラクター対策メモ**<br><br>
対戦中に発見した「あのキャラクターの倒し方」「あの強い技の対策法」をメモして保存することができます。この機能により、誰かと本気で対戦するときだけではなく、一人でじっくり遊ぶ時にまでもユーザーに価値を提供します。
<br>

# 学んだこと

### 設計の大切さ
今作が個人として初のWebアプリ制作であるため、設計段階から勉強しながら探り探りな状態でした。そのため、設計も最低限の仕上がりのまま「見切り発車」の状態で下流工程に入ってしまいました。その結果、コーディング中に一度骨組みから大きく見直すという事態に陥ってしまいます。個人で作るWebアプリなのですぐに軌道修正できましたが、これがもっと規模の大きいチーム開発であったらどれほどの被害になっていたのだろうと考えてしまいます。上流工程に対する考え方・見方を大きく変えさせられた出来事でした。
<br>
### 個人開発とチーム開発
私は過去に一度チーム開発に関わったことがあります。プロジェクトリーダーとしてチームの方向を導きながら、同時にメインプログラマーとして開発にも尽力しました。実務未経験の身には少しばかり重い仕事量と責任。膨大な作業に追われる日々の中、私の中に徐々に鬱積が募りあがります。「自分一人でやった方が楽なんじゃないだろうか」……。チームの仕事を考え割り振るために費やす労力を、全てコーディングに充てられればどれほど効率が上がるか。
しかし今回、この個人開発という経験を通じて、チーム開発の利点についても考えさせられました。チーム開発は組織である以上、個人開発では不要な雑務も生じます。しかしそれと同時に、雑務にかけた労力以上の利益も生じるのです。「仕事を分担できる」「分からない点があれば教え合える」という効率面での利益、そして「支え合える仲間がいる」という精神面での利益。
チーム開発と個人開発。どちらもまだまだ未熟ではありますが、双方の特色や違いについて身をもって学ぶことができました。


