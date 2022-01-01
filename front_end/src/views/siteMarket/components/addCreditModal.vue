<!--import { Form, Input, InputNumber, Button } from 'antd';-->

<!--const layout = {-->
<!--labelCol: {-->
<!--span: 8,-->
<!--},-->
<!--wrapperCol: {-->
<!--span: 16,-->
<!--},-->
<!--};-->
<!--const validateMessages = {-->
<!--required: '${label} is required!',-->
<!--types: {-->
<!--email: '${label} is not validate email!',-->
<!--number: '${label} is not a validate number!',-->
<!--},-->
<!--number: {-->
<!--range: '${label} must be between ${min} and ${max}',-->
<!--},-->
<!--};-->

<!--const Demo = () => {-->
<!--const onFinish = values => {-->
<!--console.log(values);-->
<!--};-->

<!--return (-->
<!--<Form {...layout} name="nest-messages" onFinish={onFinish} validateMessages={validateMessages}>-->
<!--    <Form.Item-->
<!--            name={['user', 'name']}-->
<!--    label="Name"-->
<!--    rules={[-->
<!--    {-->
<!--    required: true,-->
<!--    },-->
<!--    ]}-->
<!--    >-->
<!--    <Input />-->
<!--    </Form.Item>-->
<!--    <Form.Item-->
<!--            name={['user', 'email']}-->
<!--    label="Email"-->
<!--    rules={[-->
<!--    {-->
<!--    type: 'email',-->
<!--    },-->
<!--    ]}-->
<!--    >-->
<!--    <Input />-->
<!--    </Form.Item>-->
<!--    <Form.Item-->
<!--            name={['user', 'age']}-->
<!--    label="Age"-->
<!--    rules={[-->
<!--    {-->
<!--    type: 'number',-->
<!--    min: 0,-->
<!--    max: 99,-->
<!--    },-->
<!--    ]}-->
<!--    >-->
<!--    <InputNumber />-->
<!--    </Form.Item>-->
<!--    <Form.Item name={['user', 'website']} label="Website">-->
<!--    <Input />-->
<!--    </Form.Item>-->
<!--    <Form.Item name={['user', 'introduction']} label="Introduction">-->
<!--    <Input.TextArea />-->
<!--    </Form.Item>-->
<!--    <Form.Item wrapperCol={{ ...layout.wrapperCol, offset: 8 }}>-->
<!--        <Button type="primary" htmlType="submit">-->
<!--            Submit-->
<!--        </Button>-->
<!--    </Form.Item>-->
<!--</Form>-->
<!--);-->
<!--};-->

<!--ReactDOM.render(<Demo />, mountNode);-->

<!--<template>-->

<!--    <a-modal-->
<!--            :visible="addHotelModalVisible"-->
<!--            title="信用充值"-->
<!--            cancelText="取消"-->
<!--            okText="确定"-->
<!--            @cancel="cancel"-->
<!--            @ok="handleSubmit"-->
<!--    >-->
<!--        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">-->
<!--            <a-form-item label="用户ID">-->
<!--                <a-input-->
<!--                        placeholder="请填写用户ID"-->
<!--                        v-decorator="['uid', { rules: [{ required: true, message: '请填写用户ID' }] }]"-->
<!--                />-->
<!--            </a-form-item>-->
<!--            <a-form-item label="用户充值信用值" v-bind="formItemLayout">-->
<!--                <a-input-->
<!--                        placeholder="请填用户充值信用值"-->
<!--                        v-decorator="['credit', { rules: [{ required: true, message: '请填写用户充值信用值' }] }]"-->
<!--                />-->
<!--            </a-form-item>-->
<!--        </a-form>-->
<!--    </a-modal>-->
<!--</template>-->

<!--<script>-->
<!--    import { mapGetters, mapMutations, mapActions } from 'vuex'-->
<!--    export default {-->
<!--        name: 'addHotelModal',-->
<!--        data() {-->
<!--            return {-->
<!--                formItemLayout: {-->
<!--                    labelCol: {-->
<!--                        xs: { span: 12 },-->
<!--                        sm: { span: 6 },-->
<!--                    },-->
<!--                    wrapperCol: {-->
<!--                        xs: { span: 24 },-->
<!--                        sm: { span: 16 },-->
<!--                    },-->
<!--                },-->
<!--            }-->
<!--        },-->
<!--        computed: {-->
<!--            ...mapGetters([-->
<!--                'userId',-->
<!--                'addHotelParams',-->
<!--                'addHotelModalVisible'-->
<!--            ])-->
<!--        },-->
<!--        beforeCreate() {-->
<!--            this.form = this.$form.createForm(this, { name: 'addCreditModal' });-->
<!--        },-->
<!--        mounted() {-->

<!--        },-->
<!--        methods: {-->
<!--            ...mapMutations([-->
<!--                'set_addCreditParams',-->
<!--                'set_addCreditModalVisible'-->
<!--            ]),-->
<!--            ...mapActions([-->
<!--                'addCredit'-->
<!--            ]),-->
<!--            cancel() {-->
<!--                this.set_addCreditModalVisible(false)-->
<!--            },-->
<!--            handleSubmit(e) {-->
<!--                e.preventDefault();-->
<!--                this.form.validateFieldsAndScroll((err, values) => {-->
<!--                    if (!err) {-->
<!--                        const data = {-->
<!--                            userId: this.form.getFieldValue('uid'),-->
<!--                            credit: this.form.getFieldValue('credit'),-->
<!--                        }-->
<!--                        this.set_addCreditParams(data)-->
<!--                        this.addHotel()-->
<!--                    }-->
<!--                });-->
<!--            },-->
<!--        }-->
<!--    }-->
<!--</script>-->